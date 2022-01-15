package com.gizone.client.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gizone.client.constant.HkFace;
import com.gizone.client.utils.HexUtil;
import com.gizone.client.sdk.hk.face.linux.HCNetSDK;
import com.gizone.client.utils.HkFaceAlaramInfoDwMajorEnum;
import com.sun.jna.Pointer;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class FMSGCallBack_V31 implements HCNetSDK.FMSGCallBack_V31 {

    //lCommand 上传消息类型  pAlarmer 报警设备信息  pAlarmInfo  报警信息   dwBufLen 报警信息缓存大小   pUser  用户数据
    @Override
    public boolean invoke(int lCommand, HCNetSDK.NET_DVR_ALARMER pAlarmer, Pointer pAlarmInfo, int dwBufLen, Pointer pUser) {
        try {
            //报警类型
            String sAlarmType = new String();
            //报警时间
            Date today = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = dateFormat.format(today);
            String[] sIP = new String[2];
            sAlarmType = new String("lCommand=") + lCommand;
            log.info("报警类型 sAlarmType => {} lCommand => {} ", sAlarmType, lCommand);
            //lCommand是传的报警类型
            switch (lCommand) {
                case HCNetSDK.COMM_UPLOAD_FACESNAP_RESULT: //人脸识别结果上传
                    System.out.println("开始人脸抓拍:" + HCNetSDK.COMM_UPLOAD_FACESNAP_RESULT);
                    //实时人脸抓拍上传
                    HCNetSDK.NET_VCA_FACESNAP_RESULT strFaceSnapInfo = new HCNetSDK.NET_VCA_FACESNAP_RESULT();
                    strFaceSnapInfo.write();
                    Pointer pFaceSnapInfo = strFaceSnapInfo.getPointer();
                    System.out.println("pFaceSnapInfo:::" + pFaceSnapInfo + "\n");
                    System.out.println("strFaceSnapInfo" + strFaceSnapInfo + "\n");
                    pFaceSnapInfo.write(0, pAlarmInfo.getByteArray(0, strFaceSnapInfo.size()), 0, strFaceSnapInfo.size());
                    strFaceSnapInfo.read();
                    sAlarmType = sAlarmType + "：人脸抓拍上传，人脸评分：" + strFaceSnapInfo.dwFaceScore + "，年龄段：" + strFaceSnapInfo.struFeature.byAgeGroup + "，性别：" + strFaceSnapInfo.struFeature.bySex;

                    System.out.println("" + sAlarmType + "");
                    //报警设备IP地址
                    sIP = new String(strFaceSnapInfo.struDevInfo.struDevIP.sIpV4).split("\0", 2);
                    System.out.println(sIP);
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss"); //设置日期格式
                    String time = df.format(new Date()); // new Date()为获取当前系统时间
                    //人脸图片写文件
                    try {
                        FileOutputStream small = new FileOutputStream(System.getProperty("user.dir") + "\\pic\\" + time + "small.jpg");
                        FileOutputStream big = new FileOutputStream(System.getProperty("user.dir") + "\\pic\\" + time + "big.jpg");

                        if (strFaceSnapInfo.dwFacePicLen > 0) {
                            try {
                                small.write(strFaceSnapInfo.pBuffer1.getByteArray(0, strFaceSnapInfo.dwFacePicLen), 0, strFaceSnapInfo.dwFacePicLen);
                                small.close();
                            } catch (IOException ex) {
                                log.error("人脸识别结果上传 1 ", ex);
                            }

                        }
                        if (strFaceSnapInfo.dwFacePicLen > 0) {
                            try {
                                big.write(strFaceSnapInfo.pBuffer2.getByteArray(0, strFaceSnapInfo.dwBackgroundPicLen), 0, strFaceSnapInfo.dwBackgroundPicLen);
                                big.close();
                            } catch (IOException ex) {
                                log.error("人脸识别结果上传 2 ", ex);
                            }
                        }
                    } catch (FileNotFoundException ex) {
                        log.error("人脸识别结果上传 3 ", ex);
                    }
                    break;
                case HCNetSDK.COMM_SNAP_MATCH_ALARM: //黑名单比对结果上传
                    //人脸黑名单比对报警
                    HCNetSDK.NET_VCA_FACESNAP_MATCH_ALARM strFaceSnapMatch = new HCNetSDK.NET_VCA_FACESNAP_MATCH_ALARM();
                    strFaceSnapMatch.write();
                    Pointer pFaceSnapMatch = strFaceSnapMatch.getPointer();
                    pFaceSnapMatch.write(0, pAlarmInfo.getByteArray(0, strFaceSnapMatch.size()), 0, strFaceSnapMatch.size());
                    strFaceSnapMatch.read();

                    if ((strFaceSnapMatch.dwSnapPicLen > 0) && (strFaceSnapMatch.byPicTransType == 0)) {
                        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                        String newName = sf.format(new Date());
                        FileOutputStream fout;
                        try {
                            String filename = System.getProperty("user.dir") + "\\pic\\" + newName + "_pSnapPicBuffer" + ".jpg";
                            fout = new FileOutputStream(filename);
                            //将字节写入文件
                            long offset = 0;
                            ByteBuffer buffers = strFaceSnapMatch.pSnapPicBuffer.getByteBuffer(offset, strFaceSnapMatch.dwSnapPicLen);
                            byte[] bytes = new byte[strFaceSnapMatch.dwSnapPicLen];
                            buffers.rewind();
                            buffers.get(bytes);
                            fout.write(bytes);
                            fout.close();
                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    if ((strFaceSnapMatch.struSnapInfo.dwSnapFacePicLen > 0) && (strFaceSnapMatch.byPicTransType == 0)) {
                        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                        String newName = sf.format(new Date());
                        FileOutputStream fout;
                        try {
                            String filename = System.getProperty("user.dir") + "\\pic\\" + newName + "_struSnapInfo_pBuffer1" + ".jpg";
                            fout = new FileOutputStream(filename);
                            //将字节写入文件
                            long offset = 0;
                            ByteBuffer buffers = strFaceSnapMatch.struSnapInfo.pBuffer1.getByteBuffer(offset, strFaceSnapMatch.struSnapInfo.dwSnapFacePicLen);
                            byte[] bytes = new byte[strFaceSnapMatch.struSnapInfo.dwSnapFacePicLen];
                            buffers.rewind();
                            buffers.get(bytes);
                            fout.write(bytes);
                            fout.close();
                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                    break;
                case HCNetSDK.COMM_ALARM_ACS: //门禁主机报警信息
                    HCNetSDK.NET_DVR_ACS_ALARM_INFO strACSInfo = new HCNetSDK.NET_DVR_ACS_ALARM_INFO();
                    strACSInfo.write();
                    Pointer pACSInfo = strACSInfo.getPointer();
                    pACSInfo.write(0, pAlarmInfo.getByteArray(0, strACSInfo.size()), 0, strACSInfo.size());
                    strACSInfo.read();
                    log.info("卡号 =>>> {}", new String(strACSInfo.struAcsEventInfo.byCardNo).trim());
                    String s1 = HexUtil.convertDecToHexNot(String.valueOf(strACSInfo.dwMajor));
                    String s2 = HexUtil.convertDecToHexNot(String.valueOf(strACSInfo.dwMinor));
                    sAlarmType = sAlarmType + "：门禁主机报警信息，卡号：" + new String(strACSInfo.struAcsEventInfo.byCardNo).trim() + "，卡类型：" +
                            strACSInfo.struAcsEventInfo.byCardType + "，报警主类型：" + HkFaceAlaramInfoDwMajorEnum.getMeaning(s1) +
                            "，报警次类型：" + HkFaceAlaramInfoDwMajorEnum.getChildMeaning(s1, s2);
                    log.info("门禁报警 =>> {}", sAlarmType);
                    //人脸识别
                    if (strACSInfo.dwPicDataLen > 0) {
                        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                        String newName = sf.format(new Date());
                        FileOutputStream fout;
                        try {
                            log.info("卡号 =>> {}", new String(strACSInfo.struAcsEventInfo.byCardNo).trim());
                            String filename = System.getProperty("user.dir") + String.format("/tmp/%s%s.%s", new String(pAlarmer.sDeviceIP).trim() +
                                    "_byface[" + new String(strACSInfo.struAcsEventInfo.byCardNo).trim() +
                                    "_" + newName, HkFace.TMP_TAG, "jpg");
//                            String filename = ".\\pic\\" + new String(pAlarmer.sDeviceIP).trim() +
//                                    "_byCardNo[" + new String(strIDCardInfo.struIDCardCfg.byIDNum).trim() +
//                                    "_" + newName + "_IDInfoPic.jpg";
                            fout = new FileOutputStream(filename);
                            //将字节写入文件
                            long offset = 0;
                            ByteBuffer buffers = strACSInfo.pPicData.getByteBuffer(offset, strACSInfo.dwPicDataLen);
                            byte[] bytes = new byte[strACSInfo.dwPicDataLen];
                            buffers.rewind();
                            buffers.get(bytes);
                            fout.write(bytes);
                            fout.close();
                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    String s = JSONObject.toJSONString(strACSInfo);
                    log.info(s);
                    //报警设备IP地址
                    sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                    break;
                case HCNetSDK.COMM_ID_INFO_ALARM: //身份证信息
                    HCNetSDK.NET_DVR_ID_CARD_INFO_ALARM strIDCardInfo = new HCNetSDK.NET_DVR_ID_CARD_INFO_ALARM();
                    strIDCardInfo.write();
                    Pointer pIDCardInfo = strIDCardInfo.getPointer();
                    pIDCardInfo.write(0, pAlarmInfo.getByteArray(0, strIDCardInfo.size()), 0, strIDCardInfo.size());
                    strIDCardInfo.read();

                    sAlarmType = sAlarmType + "：门禁身份证刷卡信息，身份证号码：" + new String(strIDCardInfo.struIDCardCfg.byIDNum).trim() + "，姓名：" +
                            new String(strIDCardInfo.struIDCardCfg.byName).trim() + "，报警主类型：" + strIDCardInfo.dwMajor + "，报警次类型：" + strIDCardInfo.dwMinor;

                    //报警设备IP地址
                    sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);

                    //身份证图片
                    if (strIDCardInfo.dwPicDataLen > 0) {
                        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                        String newName = sf.format(new Date());
                        FileOutputStream fout;
                        try {
                            log.info("卡号 =>> {}", new String(strIDCardInfo.struIDCardCfg.byIDNum).trim());
                            String filename = System.getProperty("user.dir") + String.format("/tmp/%s%s.%s", new String(pAlarmer.sDeviceIP).trim() +
                                    "_byCardNo[" + new String(strIDCardInfo.struIDCardCfg.byIDNum).trim() +
                                    "_" + newName, HkFace.TMP_TAG, "jpg");
//                            String filename = ".\\pic\\" + new String(pAlarmer.sDeviceIP).trim() +
//                                    "_byCardNo[" + new String(strIDCardInfo.struIDCardCfg.byIDNum).trim() +
//                                    "_" + newName + "_IDInfoPic.jpg";
                            fout = new FileOutputStream(filename);
                            //将字节写入文件
                            long offset = 0;
                            ByteBuffer buffers = strIDCardInfo.pPicData.getByteBuffer(offset, strIDCardInfo.dwPicDataLen);
                            byte[] bytes = new byte[strIDCardInfo.dwPicDataLen];
                            buffers.rewind();
                            buffers.get(bytes);
                            fout.write(bytes);
                            fout.close();
                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    //抓拍图片
                    if (strIDCardInfo.dwCapturePicDataLen > 0) {
                        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                        String newName = sf.format(new Date());
                        FileOutputStream fout;
                        try {
                            String filename = ".\\pic\\" + new String(pAlarmer.sDeviceIP).trim() +
                                    "_byCardNo[" + new String(strIDCardInfo.struIDCardCfg.byIDNum).trim() +
                                    "_" + newName + "_IDInfoCapturePic.jpg";
                            fout = new FileOutputStream(filename);
                            //将字节写入文件
                            long offset = 0;
                            ByteBuffer buffers = strIDCardInfo.pCapturePicData.getByteBuffer(offset, strIDCardInfo.dwCapturePicDataLen);
                            byte[] bytes = new byte[strIDCardInfo.dwCapturePicDataLen];
                            buffers.rewind();
                            buffers.get(bytes);
                            fout.write(bytes);
                            fout.close();
                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    break;
                case HCNetSDK.COMM_ISAPI_ALARM: //ISAPI协议报警信息
                    HCNetSDK.NET_DVR_ALARM_ISAPI_INFO struEventISAPI = new HCNetSDK.NET_DVR_ALARM_ISAPI_INFO();
                    struEventISAPI.write();
                    Pointer pEventISAPI = struEventISAPI.getPointer();
                    pEventISAPI.write(0, pAlarmInfo.getByteArray(0, struEventISAPI.size()), 0, struEventISAPI.size());
                    struEventISAPI.read();
                    sAlarmType = sAlarmType + "：ISAPI协议报警信息, 数据格式:" + struEventISAPI.byDataType +
                            ", 图片个数:" + struEventISAPI.byPicturesNumber;

                    sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);

                    SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMddHHmmss");
                    String curTime = sf1.format(new Date());
                    FileOutputStream foutdata;
                    try {
                        String jsonfilename = ".\\pic\\" + new String(pAlarmer.sDeviceIP).trim() + curTime + "_ISAPI_Alarm_" + ".json";
                        foutdata = new FileOutputStream(jsonfilename);
                        //将字节写入文件
                        ByteBuffer jsonbuffers = struEventISAPI.pAlarmData.getByteBuffer(0, struEventISAPI.dwAlarmDataLen);
                        byte[] jsonbytes = new byte[struEventISAPI.dwAlarmDataLen];
                        jsonbuffers.rewind();
                        jsonbuffers.get(jsonbytes);
                        foutdata.write(jsonbytes);
                        foutdata.close();
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    for (int i = 0; i < struEventISAPI.byPicturesNumber; i++) {
                        HCNetSDK.NET_DVR_ALARM_ISAPI_PICDATA struPicData = new HCNetSDK.NET_DVR_ALARM_ISAPI_PICDATA();
                        struPicData.write();
                        Pointer pPicData = struPicData.getPointer();
                        pPicData.write(0, struEventISAPI.pPicPackData.getByteArray(i * struPicData.size(), struPicData.size()), 0, struPicData.size());
                        struPicData.read();

                        FileOutputStream fout;
                        try {
                            String filename = ".\\pic\\" + new String(pAlarmer.sDeviceIP).trim() + curTime +
                                    "_ISAPIPic_" + i + "_" + new String(struPicData.szFilename).trim() + ".jpg";
                            fout = new FileOutputStream(filename);
                            //将字节写入文件
                            long offset = 0;
                            ByteBuffer buffers = struPicData.pPicData.getByteBuffer(offset, struPicData.dwPicLen);
                            byte[] bytes = new byte[struPicData.dwPicLen];
                            buffers.rewind();
                            buffers.get(bytes);
                            fout.write(bytes);
                            fout.close();
                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    break;
                default:
                    //报警设备IP地址
                    sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                    break;
            }
        } catch (Exception ex) {
            log.error("error ", ex);
        }
        return true;
    }
}