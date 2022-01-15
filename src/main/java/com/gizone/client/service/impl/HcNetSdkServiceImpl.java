package com.gizone.client.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.gizone.client.constant.HkFace;
import com.gizone.client.constant.HkfaceEnum;
import com.gizone.client.pojo.param.HkFaceDelUserParam;
import com.gizone.client.pojo.param.HkFaceSearchCardListParam;
import com.gizone.client.pojo.param.HkFaceSearchUserListParam;
import com.gizone.client.pojo.po.*;
import com.gizone.client.pojo.vo.HkFaceBaseVo;
import com.gizone.client.pojo.vo.HkFaceSearchCardVo;
import com.gizone.client.pojo.vo.HkFaceSearchPersonVo;
import com.gizone.client.pojo.vo.HkFaceSearchUserInfoVo;
import com.gizone.client.sdk.hk.face.linux.HCNetSDK;
import com.gizone.client.service.HcNetSdkService;
import com.gizone.client.utils.DateUtils;
import com.gizone.client.utils.FileUtil;
import com.gizone.client.utils.ImageUtil;
import com.gizone.client.utils.JsonResult;
import com.sun.jna.ptr.IntByReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yyt
 */
@Service
@Slf4j
public class HcNetSdkServiceImpl implements HcNetSdkService {

    @Autowired
    public HCNetSDK hcNetSDK;
    private String tmpDir = HkFace.TMP_DIR;
    public static final int ISAPI_DATA_LEN = 1024 * 1024;
    public static final int ISAPI_STATUS_LEN = 4 * 4096;

    @Override
    public JsonResult searchPerson(HkFaceSearchPO po) {

        //登陆
        HkFaceBaseSdkPO sdkPO = this.Login(po);
        JsonResult jsonResult = JsonResult.failed();
        try {
            jsonResult = this.searchUserList(sdkPO, po);
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("searchPerson ", e);
        } finally {
            end(sdkPO);
        }

        return jsonResult;
    }

    @Override
    public JsonResult addPerson(HkFaceAddOrUpdatePO po) {

        //登陆
        HkFaceBaseSdkPO sdkPO = this.Login(po);
        JsonResult jsonResult = JsonResult.failed();
        try {
            sdkPO.setHkfaceEnum(HkfaceEnum.ADD_PERSION_URL);
            HkFaceBaseVo hkFaceBaseVo = this.addUserInfo(sdkPO, po);
            if(ObjectUtil.isNotNull(hkFaceBaseVo) && hkFaceBaseVo.getDwState() == 1000 && hkFaceBaseVo.getStatusCode() == 1){
                this.addCard(sdkPO, po);
                if (StringUtils.isNotBlank(po.getFaceUrl())) {
                    this.addMultiFace(sdkPO, po);
                }
                jsonResult = JsonResult.success();
            }else{
                jsonResult.setMsg(hkFaceBaseVo.getStrResult());
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(e.toString());
        } finally {
            end(sdkPO);
        }

        return jsonResult;
    }

    @Override
    public JsonResult batchAddPerson(HkFaceBatchAddPO po) {
        //登陆
        HkFaceBaseSdkPO sdkPO = this.Login(po);
        try {
            List<HkFaceAddOrUpdatePO> persons = po.getPersons();
            for (HkFaceAddOrUpdatePO person : persons) {
                sdkPO.setHkfaceEnum(HkfaceEnum.ADD_PERSION_URL);
                this.addUserInfo(sdkPO, person);
                this.addCard(sdkPO, person);
                if (StringUtils.isNotBlank(person.getFaceUrl())) {
                    this.addMultiFace(sdkPO, person);
                }
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(e.toString());
        } finally {
            end(sdkPO);
        }

        return JsonResult.success();

    }

    @Override
    public JsonResult editPerson(HkFaceAddOrUpdatePO po) {
        //登陆
        HkFaceBaseSdkPO sdkPO = this.Login(po);
        try {
            sdkPO.setHkfaceEnum(HkfaceEnum.ADD_OR_UPDATE_PERSION_URL);
            this.addUserInfo(sdkPO, po);
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(e.toString());
        } finally {
            end(sdkPO);
        }

        return JsonResult.success();
    }

    @Override
    public JsonResult delPerson(HkFaceDelPO po) {
        //登陆
        HkFaceBaseSdkPO sdkPO = this.Login(po);
        try {
            this.delUserInfo(sdkPO, po);
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("delPerson ", e);
        } finally {
            end(sdkPO);
        }

        return JsonResult.success();
    }

    @Override
    public JsonResult searchCard(HkFaceSearchCardPO po) {
        //登陆
        HkFaceBaseSdkPO sdkPO = this.Login(po);
        JsonResult jsonResult = JsonResult.failed();
        try {
            jsonResult = this.searchCardByEmployeeNo(sdkPO, po);
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(e.toString());
        } finally {
            end(sdkPO);
        }

        return jsonResult;
    }

    @Override
    public JsonResult addCard(HkFaceAddCardPO po) {
        //登陆
        HkFaceBaseSdkPO sdkPO = this.Login(po);
        try {
            HkFaceAddOrUpdatePO hkFaceAddOrUpdatePO = BeanUtil.copyProperties(po, HkFaceAddOrUpdatePO.class);
            this.addCard(sdkPO, hkFaceAddOrUpdatePO);
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(e.toString());
        } finally {
            end(sdkPO);
        }

        return JsonResult.success();
    }

    @Override
    public JsonResult startArmed(HkFaceSearchPO po) {
        //登陆
        HkFaceBaseSdkPO sdkPO = this.Login(po);
        this.armed(sdkPO, po);
        return JsonResult.success();
    }

    @Override
    public JsonResult closeArmed(HkFaceSearchPO po) {
        String fileName = tmpDir + "nativeLong";
        String s = FileUtil.readDataToFile(fileName);
        if (StringUtils.isBlank(s)) {
            return JsonResult.failed("未布防");
        }
        int nativeLong = Integer.parseInt(s);
        if (hcNetSDK.NET_DVR_CloseAlarmChan_V30(nativeLong)) {
            FileUtil.saveDataToFile(fileName, "");
            return JsonResult.success();
        }
        return JsonResult.failed();
    }

    /**
     * 结束后操作
     *
     * @param sdkPO
     */
    private void end(HkFaceBaseSdkPO sdkPO) {
        hcNetSDK.NET_DVR_Logout(sdkPO.getLUserID());
    }

    public HkFaceBaseSdkPO Login(HkFaceBasePO po) {
        HkFaceBaseSdkPO sdkPO = new HkFaceBaseSdkPO();
        //注册
        //设备登录信息
        HCNetSDK.NET_DVR_USER_LOGIN_INFO m_strLoginInfo = new HCNetSDK.NET_DVR_USER_LOGIN_INFO();

        //设备ip地址
        String m_sDeviceIP = po.getIp();
        m_strLoginInfo.sDeviceAddress = new byte[HCNetSDK.NET_DVR_DEV_ADDRESS_MAX_LEN];
        System.arraycopy(m_sDeviceIP.getBytes(), 0, m_strLoginInfo.sDeviceAddress, 0, m_sDeviceIP.length());

        //设备用户名
        String m_sUsername = po.getUserName();
        m_strLoginInfo.sUserName = new byte[HCNetSDK.NET_DVR_LOGIN_USERNAME_MAX_LEN];
        System.arraycopy(m_sUsername.getBytes(), 0, m_strLoginInfo.sUserName, 0, m_sUsername.length());

        //设备密码
        String m_sPassword = po.getPassWord();
        m_strLoginInfo.sPassword = new byte[HCNetSDK.NET_DVR_LOGIN_PASSWD_MAX_LEN];
        System.arraycopy(m_sPassword.getBytes(), 0, m_strLoginInfo.sPassword, 0, m_sPassword.length());

        m_strLoginInfo.wPort = po.getPort();
        //是否异步登录：0- 否，1- 是
        m_strLoginInfo.bUseAsynLogin = false;
        m_strLoginInfo.write();

        //设备信息
        HCNetSDK.NET_DVR_DEVICEINFO_V40 m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V40();
        int lUserID = hcNetSDK.NET_DVR_Login_V40(m_strLoginInfo, m_strDeviceInfo);
        sdkPO.setLUserID(lUserID);
        if (lUserID == -1) {
            log.info("登录失败，错误码为 {}", hcNetSDK.NET_DVR_GetLastError());
            return sdkPO;
        } else {
            log.info("登录成功！");
            int iCharEncodeType = m_strDeviceInfo.byCharEncodeType;
            sdkPO.setICharEncodeType(iCharEncodeType);
        }
        return sdkPO;
    }

    /**
     * 布防
     *
     * @param sdkPO
     * @param po
     */
    public void armed(HkFaceBaseSdkPO sdkPO, HkFaceSearchPO po) {
        HCNetSDK.FMSGCallBack_V31 fmsgCallBack_v31 = new FMSGCallBack_V31();
        boolean b = hcNetSDK.NET_DVR_SetDVRMessageCallBack_V31(fmsgCallBack_v31, null);
        //如果设置报警回调失败，获取错误码
        if (!b) {
            System.out.println("SetDVRMessageCallBack failed, error code=" + hcNetSDK.NET_DVR_GetLastError());
        }
        //建立报警上传通道（布防）
        //布防参数
        HCNetSDK.NET_DVR_SETUPALARM_PARAM net_dvr_setupalarm_param = new HCNetSDK.NET_DVR_SETUPALARM_PARAM();
        int nativeLong = hcNetSDK.NET_DVR_SetupAlarmChan_V41(sdkPO.getLUserID(), net_dvr_setupalarm_param);
        //如果布防失败返回-1
        if (nativeLong < 0) {
            System.out.println("SetupAlarmChan failed, error code=" + hcNetSDK.NET_DVR_GetLastError());
            //注销
            hcNetSDK.NET_DVR_Logout(sdkPO.getLUserID());
        }
        //保存 nativeLong
        String fileName = tmpDir + "nativeLong";
        FileUtil.saveDataToFile(fileName, String.valueOf(nativeLong));

    }

    /**
     * 查询用户
     *
     * @param sdkPO
     * @param po
     * @throws Exception
     */
    public JsonResult searchUserList(HkFaceBaseSdkPO sdkPO, HkFaceSearchPO po) throws Exception {
        sdkPO.setHkfaceEnum(HkfaceEnum.SEARCH_PERSION_URL);
        //数组
        int lHandler = getHandler(sdkPO);
        if (lHandler < 0) {
            log.info("{} NET_DVR_StartRemoteConfig 失败,错误码为 {}", sdkPO.getHkfaceEnum().getName(), hcNetSDK.NET_DVR_GetLastError());
            return JsonResult.failed();
        } else {

            List hkFaceSearchUserInfoVos = new ArrayList<HkFaceSearchUserInfoVo>();
            int searchResultPosition = 0;
            String searchID = String.format("%s-%s", sdkPO.getLUserID(), DateUtils.time());
            while (true) {
                HkFaceSearchUserListParam hkFaceSearchUserListParam = HkFaceSearchUserListParam.reParam(searchID, searchResultPosition, 10, null);
                String strInbuff = JSONObject.toJSONString(hkFaceSearchUserListParam);
                log.info("{} }json报文: {}", sdkPO.getHkfaceEnum().getName(), strInbuff);

                //把string传递到Byte数组中，后续用.getPointer()方法传入指针地址中。
                HCNetSDK.BYTE_ARRAY ptrInbuff = new HCNetSDK.BYTE_ARRAY(strInbuff.length());
                System.arraycopy(strInbuff.getBytes(), 0, ptrInbuff.byValue, 0, strInbuff.length());
                ptrInbuff.write();
                HkFaceBaseVo hkFaceBaseVo = sendRequest(sdkPO, lHandler, strInbuff.length(), ptrInbuff);
                if (ObjectUtil.isNull(hkFaceBaseVo)) {
                    break;
                }
                HkFaceSearchPersonVo hkFaceSearchPO = JSONObject.parseObject(hkFaceBaseVo.getStrResult(), HkFaceSearchPersonVo.class);
                List<HkFaceSearchUserInfoVo> userInfo = hkFaceSearchPO.getUserInfoSearch().getUserInfo();
                if (userInfo.size() > 0) {
                    hkFaceSearchUserInfoVos.add(userInfo);
                }
                if ("MORE".equals(hkFaceSearchPO.getUserInfoSearch().getResponseStatusStrg())) {
                    searchResultPosition = searchResultPosition + hkFaceSearchPO.getUserInfoSearch().getNumOfMatches();
                    continue;
                }
                break;
            }
            if (!hcNetSDK.NET_DVR_StopRemoteConfig(lHandler)) {
                log.info("{} NET_DVR_StopRemoteConfig接口调用失败，错误码：{}", sdkPO.getHkfaceEnum().getName(), hcNetSDK.NET_DVR_GetLastError());
            } else {
                log.info("{} NET_DVR_StopRemoteConfig接口成功", sdkPO.getHkfaceEnum().getName());
            }
            return JsonResult.success(hkFaceSearchUserInfoVos);
        }
    }

    /**
     * 添加用户
     *
     * @param sdkPO
     * @param po
     * @throws Exception
     */
    public HkFaceBaseVo addUserInfo(HkFaceBaseSdkPO sdkPO, HkFaceAddOrUpdatePO po) throws Exception {
        //数组
        int lHandler = getHandler(sdkPO);
        if (lHandler < 0) {
            log.info("{} NET_DVR_StartRemoteConfig 失败,错误码为 {}", sdkPO.getHkfaceEnum().getName(), hcNetSDK.NET_DVR_GetLastError());
            return null;
        } else {
            log.info("{} NET_DVR_StartRemoteConfig 成功!", sdkPO.getHkfaceEnum().getName());
            //根据iCharEncodeType判断，如果iCharEncodeType返回6，则是UTF-8编码。
            byte[] Name = po.getName().getBytes("utf-8");
            //如果是0或者1或者2，则是GBK编码
            String beginTime = DateUtils.getISO8601Time(po.getBeginTime());
            String endTime = DateUtils.getISO8601Time(po.getEndTime());

            //将中文字符编码之后用数组拷贝的方式，避免因为编码导致的长度问题
            String strInBuffer1 = "{\"UserInfo\":{\"Valid\":{\"beginTime\":\"" + beginTime + "\",\"enable\":true,\"endTime\":\"" + endTime + "\"},\"checkUser\":false,\"doorRight\":\"1\",\"RightPlan\":[{\"doorNo\": 1,\"planTemplateNo\": \"1,3,5\"}],\"employeeNo\":\""
                    + po.getEmployeeNo() + "\",\"floorNumber\":1,\"maxOpenDoorTime\":0,\"name\":\"";
            String strInBuffer2 = "\",\"openDelayEnabled\":false,\"password\":\"" + po.getUserPwd() + "\",\"roomNumber\":1,\"userType\":\"normal\"}}";
            int iStringSize = Name.length + strInBuffer1.length() + strInBuffer2.length();

            HCNetSDK.BYTE_ARRAY ptrByte = new HCNetSDK.BYTE_ARRAY(iStringSize);
            System.arraycopy(strInBuffer1.getBytes(), 0, ptrByte.byValue, 0, strInBuffer1.length());
            System.arraycopy(Name, 0, ptrByte.byValue, strInBuffer1.length(), Name.length);
            System.arraycopy(strInBuffer2.getBytes(), 0, ptrByte.byValue, strInBuffer1.length() + Name.length, strInBuffer2.length());
            ptrByte.write();

            log.info(new String(ptrByte.byValue));

            HkFaceBaseVo hkFaceBaseVo = sendRequest(sdkPO, lHandler, iStringSize, ptrByte);

            if (!hcNetSDK.NET_DVR_StopRemoteConfig(lHandler)) {
                log.info("{} NET_DVR_StopRemoteConfig接口调用失败，错误码：{}", sdkPO.getHkfaceEnum().getName(), hcNetSDK.NET_DVR_GetLastError());
            } else {
                log.info("{} NET_DVR_StopRemoteConfig接口成功", sdkPO.getHkfaceEnum().getName());
            }
            return hkFaceBaseVo;
        }
    }

    public void delUserInfo(HkFaceBaseSdkPO sdkPO, HkFaceDelPO po) {
        sdkPO.setHkfaceEnum(HkfaceEnum.DEL_PERSION_URL);
        String strURL = sdkPO.getHkfaceEnum().getMethod();
        HCNetSDK.BYTE_ARRAY ptrUrl = new HCNetSDK.BYTE_ARRAY(sdkPO.getHkfaceEnum().getSize());
        System.arraycopy(strURL.getBytes(), 0, ptrUrl.byValue, 0, strURL.length());
        ptrUrl.write();

        //输入删除条件
        HCNetSDK.BYTE_ARRAY ptrInBuffer = new HCNetSDK.BYTE_ARRAY(ISAPI_DATA_LEN);
        ptrInBuffer.read();
        String employeeNos = po.getEmployeeNos();
        String[] split = StringUtils.split(employeeNos, ",");
        List<String> employeeNoList = Arrays.asList(split);
        HkFaceDelUserParam hkFaceDelUserParam = HkFaceDelUserParam.reParam(employeeNoList);
        String strInbuffer = JSONObject.toJSONString(hkFaceDelUserParam);
        log.info("{} 的json报文: {}", sdkPO.getHkfaceEnum().getMethod(), strInbuffer);
        ptrInBuffer.byValue = strInbuffer.getBytes();
        ptrInBuffer.write();

        HCNetSDK.NET_DVR_XML_CONFIG_INPUT struXMLInput = new HCNetSDK.NET_DVR_XML_CONFIG_INPUT();
        struXMLInput.read();
        struXMLInput.dwSize = struXMLInput.size();
        struXMLInput.lpRequestUrl = ptrUrl.getPointer();
        struXMLInput.dwRequestUrlLen = ptrUrl.byValue.length;
        struXMLInput.lpInBuffer = ptrInBuffer.getPointer();
        struXMLInput.dwInBufferSize = ptrInBuffer.byValue.length;
        struXMLInput.write();

        HCNetSDK.BYTE_ARRAY ptrStatusByte = new HCNetSDK.BYTE_ARRAY(ISAPI_STATUS_LEN);
        ptrStatusByte.read();

        HCNetSDK.BYTE_ARRAY ptrOutByte = new HCNetSDK.BYTE_ARRAY(ISAPI_DATA_LEN);
        ptrOutByte.read();

        HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT struXMLOutput = new HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT();
        struXMLOutput.read();
        struXMLOutput.dwSize = struXMLOutput.size();
        struXMLOutput.lpOutBuffer = ptrOutByte.getPointer();
        struXMLOutput.dwOutBufferSize = ptrOutByte.size();
        struXMLOutput.lpStatusBuffer = ptrStatusByte.getPointer();
        struXMLOutput.dwStatusSize = ptrStatusByte.size();
        struXMLOutput.write();

        if (!hcNetSDK.NET_DVR_STDXMLConfig(sdkPO.getLUserID(), struXMLInput, struXMLOutput)) {
            int iErr = hcNetSDK.NET_DVR_GetLastError();
            System.out.println("NET_DVR_STDXMLConfig失败，错误号：" + iErr);
            return;

        } else {
            struXMLOutput.read();
            ptrOutByte.read();
            ptrStatusByte.read();
            String strOutXML = new String(ptrOutByte.byValue).trim();
            System.out.println("删除人员输出结果:" + strOutXML);
            String strStatus = new String(ptrStatusByte.byValue).trim();
            System.out.println("删除人员返回状态：" + strStatus);
        }
    }

    /**
     * 根据人员id查询卡号
     *
     * @param sdkPO
     * @param po
     * @throws Exception
     */
    public JsonResult searchCardByEmployeeNo(HkFaceBaseSdkPO sdkPO, HkFaceSearchCardPO po) throws Exception {
        sdkPO.setHkfaceEnum(HkfaceEnum.SEARCH_CARD_URL);
        int lHandler = getHandler(sdkPO);
        if (lHandler < 0) {
            log.info("{} NET_DVR_StartRemoteConfig 失败,错误码为 {}", sdkPO.getHkfaceEnum().getName(), hcNetSDK.NET_DVR_GetLastError());
            return JsonResult.failed();
        } else {
            log.info("{} NET_DVR_StartRemoteConfig 成功!", sdkPO.getHkfaceEnum().getName());
            String searchId = String.format("%s-%s", sdkPO.getLUserID(), DateUtils.time());
            List<String> employeeNoList = Arrays.asList(po.getEmployeeNo());
            HkFaceSearchCardListParam hkFaceSearchCardListParam = HkFaceSearchCardListParam.reParam(searchId, 0, 5, employeeNoList);
            String strInbuff = JSONObject.toJSONString(hkFaceSearchCardListParam);
            log.info("{} json报文: {}", sdkPO.getHkfaceEnum().getName(), strInbuff);

            HCNetSDK.BYTE_ARRAY ptrByte = new HCNetSDK.BYTE_ARRAY(strInbuff.length());
            System.arraycopy(strInbuff.getBytes(), 0, ptrByte.byValue, 0, strInbuff.length());
            ptrByte.write();

            log.info(new String(ptrByte.byValue));

            HkFaceBaseVo hkFaceBaseVo = sendRequest(sdkPO, lHandler, strInbuff.length(), ptrByte);

            if (!hcNetSDK.NET_DVR_StopRemoteConfig(lHandler)) {
                log.info("{} NET_DVR_StopRemoteConfig接口调用失败，错误码：{}", sdkPO.getHkfaceEnum().getName(), hcNetSDK.NET_DVR_GetLastError());
            } else {
                log.info("{} NET_DVR_StopRemoteConfig接口成功", sdkPO.getHkfaceEnum().getName());
            }
            return JsonResult.success(JSONObject.parseObject(hkFaceBaseVo.getStrResult(), HkFaceSearchCardVo.class));
        }

    }

    /**
     * 添加卡号
     *
     * @param sdkPO
     * @param po
     * @throws Exception
     */
    public void addCard(HkFaceBaseSdkPO sdkPO, HkFaceAddOrUpdatePO po) throws Exception {
        sdkPO.setHkfaceEnum(HkfaceEnum.ADD_CARD_URL);
        int lHandler = getHandler(sdkPO);
        if (lHandler < 0) {
            log.info("{} NET_DVR_StartRemoteConfig 失败,错误码为 {}", sdkPO.getHkfaceEnum().getName(), hcNetSDK.NET_DVR_GetLastError());
            return;
        } else {
            log.info("{} NET_DVR_StartRemoteConfig 成功!", sdkPO.getHkfaceEnum().getName());

            JSONObject jsonObject = new JSONObject();
            JSONObject jsonSearchCond = new JSONObject();
            jsonSearchCond.put("employeeNo", po.getEmployeeNo());
            jsonSearchCond.put("cardNo", po.getCardNo());
            jsonSearchCond.put("cardType", "normalCard");
            jsonSearchCond.put("leaderCard", "");
            jsonSearchCond.put("checkCardNo", "");
            jsonObject.put("CardInfo", jsonSearchCond);

            String strInbuff = jsonObject.toString();
            log.info("addCard json报文:" + strInbuff);

            //将中文字符编码之后用数组拷贝的方式，避免因为编码导致的长度问题
//			String strInBuffer1 = "{" +
//					"\"CardInfo\":{" +
//					"\"employeeNo\":"+strEmployeeID+"," +
//					"\"cardNo\":\"1234567890\"," +
//					"\"cardType\":\"normalCard\"," +
//					"\"leaderCard\":\"\"," +
//					"\"checkCardNo\":\n" +
//					"}}";

            HCNetSDK.BYTE_ARRAY ptrByte = new HCNetSDK.BYTE_ARRAY(strInbuff.length());
            System.arraycopy(strInbuff.getBytes(), 0, ptrByte.byValue, 0, strInbuff.length());
            ptrByte.write();

            log.info(new String(ptrByte.byValue));

            HCNetSDK.BYTE_ARRAY ptrOutuff = new HCNetSDK.BYTE_ARRAY(1024);

            IntByReference pInt = new IntByReference(0);
            int dwState = hcNetSDK.NET_DVR_SendWithRecvRemoteConfig(lHandler, ptrByte.getPointer(), strInbuff.length(), ptrOutuff.getPointer(), 1024, pInt);
            //读取返回的json并解析
            ptrOutuff.read();
            String strResult = new String(ptrOutuff.byValue).trim();
            log.info("dwState:" + dwState + ",strResult:" + strResult);

            HkFaceBaseVo hkFaceBaseVo = JSONObject.parseObject(strResult, HkFaceBaseVo.class);
//            JSONObject jsonResult = JSONObject.parseObject(strResult);
//            int statusCode = jsonResult.getInteger("statusCode");
//            String statusString = jsonResult.getString("statusString");

            resultProcessing(sdkPO, dwState, hkFaceBaseVo, lHandler);
            if (!hcNetSDK.NET_DVR_StopRemoteConfig(lHandler)) {
                log.info("{} NET_DVR_StopRemoteConfig接口调用失败，错误码：{}", sdkPO.getHkfaceEnum().getName(), hcNetSDK.NET_DVR_GetLastError());
            } else {
                log.info("{} NET_DVR_StopRemoteConfig接口成功", sdkPO.getHkfaceEnum().getName());
            }
        }

    }

    /**
     * 下发人脸
     *
     * @param sdkPO
     * @param po
     * @throws Exception
     */
    public void addMultiFace(HkFaceBaseSdkPO sdkPO, HkFaceAddOrUpdatePO po) throws Exception {
        sdkPO.setHkfaceEnum(HkfaceEnum.ADD_FACE_PIC_URL);
        //数组
        HCNetSDK.BYTE_ARRAY ptrByteArray = new HCNetSDK.BYTE_ARRAY(1024);
        String strInBuffer = sdkPO.getHkfaceEnum().getMethod();
        //字符串拷贝到数组中
        System.arraycopy(strInBuffer.getBytes(), 0, ptrByteArray.byValue, 0, strInBuffer.length());
        ptrByteArray.write();
        /*NET_DVR_JSON_CONFIG*/
        int lHandler = hcNetSDK.NET_DVR_StartRemoteConfig(sdkPO.getLUserID(), HkfaceEnum.ADD_FACE_PIC_URL.getCommand(), ptrByteArray.getPointer(), strInBuffer.length(), null, null);
        if (lHandler < 0) {
            log.info("{} NET_DVR_StartRemoteConfig 失败,错误码为 {}", sdkPO.getHkfaceEnum().getName(), hcNetSDK.NET_DVR_GetLastError());
            return;
        } else {
            log.info("{} NET_DVR_StartRemoteConfig 成功!", sdkPO.getHkfaceEnum().getName());

            String faceUrl = po.getFaceUrl();
            String fileName = String.format("%s%s.%s", DateUtils.time(), HkFace.TMP_TAG, ImageUtil.getFileSuffix(faceUrl));
            File folder = new File(tmpDir);
            if (!folder.exists() && !folder.isDirectory()) {
                folder.mkdirs();
            }
            String filePath = String.format("%s%s", tmpDir, fileName);
            File file = FileUtil.saveTempFile(faceUrl, tmpDir, fileName);
            if (file.length() / 1024 > 100) {
                ImageUtil.resize(filePath, filePath, 0.6F);
            }
            String strFilePath = file.getPath();

            HCNetSDK.NET_DVR_JSON_DATA_CFG net_dvr_json_data_cfg = new HCNetSDK.NET_DVR_JSON_DATA_CFG();

            net_dvr_json_data_cfg.read();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("faceLibType", "blackFD");
            jsonObject.put("FDID", "1");
            jsonObject.put("FPID", po.getEmployeeNo());//人脸下发关联的工号

            String strJsonData = jsonObject.toString();
            log.info("下发人脸的json报文:" + strJsonData);

            System.arraycopy(strJsonData.getBytes(), 0, ptrByteArray.byValue, 0, strJsonData.length());//字符串拷贝到数组中
            ptrByteArray.write();

            net_dvr_json_data_cfg.dwSize = net_dvr_json_data_cfg.size();
            net_dvr_json_data_cfg.lpJsonData = ptrByteArray.getPointer();
            net_dvr_json_data_cfg.dwJsonDataSize = strJsonData.length();

            FileInputStream picfile = null;
            int picdataLength = 0;
            try {
                picfile = new FileInputStream(new File(strFilePath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                picdataLength = picfile.available();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if (picdataLength < 0) {
                log.info("input file dataSize < 0");
                return;
            }

            HCNetSDK.BYTE_ARRAY ptrpicByte = new HCNetSDK.BYTE_ARRAY(picdataLength);
            try {
                picfile.read(ptrpicByte.byValue);
                picfile.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            ptrpicByte.write();
            net_dvr_json_data_cfg.dwPicDataSize = picdataLength;
            net_dvr_json_data_cfg.lpPicData = ptrpicByte.getPointer();
            net_dvr_json_data_cfg.write();

            HCNetSDK.BYTE_ARRAY ptrOutuff = new HCNetSDK.BYTE_ARRAY(1024);
            IntByReference pInt = new IntByReference(0);

            int dwState = hcNetSDK.NET_DVR_SendWithRecvRemoteConfig(lHandler, net_dvr_json_data_cfg.getPointer(), net_dvr_json_data_cfg.dwSize, ptrOutuff.getPointer(), ptrOutuff.size(), pInt);
            //读取返回的json并解析
            ptrOutuff.read();
            String strResult = new String(ptrOutuff.byValue).trim();
            log.info("dwState:" + dwState + ",strResult:" + strResult);
            if (strResult.isEmpty()) {
                return;
            }
            HkFaceBaseVo hkFaceBaseVo = JSONObject.parseObject(strResult, HkFaceBaseVo.class);
//            JSONObject jsonResult = JSONObject.parseObject(strResult);

            resultProcessing(sdkPO, dwState, hkFaceBaseVo, lHandler);
            FileUtil.deleteTempFile(new File(filePath));
            if (!hcNetSDK.NET_DVR_StopRemoteConfig(lHandler)) {
                log.info("{} NET_DVR_StopRemoteConfig接口调用失败，错误码：{}", sdkPO.getHkfaceEnum().getName(), hcNetSDK.NET_DVR_GetLastError());
            } else {
                log.info("{} NET_DVR_StopRemoteConfig接口成功", sdkPO.getHkfaceEnum().getName());
            }
        }
    }

    /**
     * 启动远程配置
     *
     * @param sdkPO
     * @return
     */
    private int getHandler(HkFaceBaseSdkPO sdkPO) {
        //数组
        HCNetSDK.BYTE_ARRAY ptrByteArray = new HCNetSDK.BYTE_ARRAY(1024);
        String strInBuffer = sdkPO.getHkfaceEnum().getMethod();
        //字符串拷贝到数组中
        System.arraycopy(strInBuffer.getBytes(), 0, ptrByteArray.byValue, 0, strInBuffer.length());
        ptrByteArray.write();
        /*NET_DVR_JSON_CONFIG*/
        return hcNetSDK.NET_DVR_StartRemoteConfig(sdkPO.getLUserID(), sdkPO.getHkfaceEnum().getCommand(), ptrByteArray.getPointer(), strInBuffer.length(), null, null);
    }

    /**
     * 发送请求
     *
     * @param sdkPO
     * @param lHandler
     * @param iStringSize
     * @throws InterruptedException
     */
    private HkFaceBaseVo sendRequest(HkFaceBaseSdkPO sdkPO, int lHandler, int iStringSize, HCNetSDK.BYTE_ARRAY ptrByte) throws InterruptedException {
        HCNetSDK.BYTE_ARRAY ptrOutuff = new HCNetSDK.BYTE_ARRAY(sdkPO.getHkfaceEnum().getSize());

        IntByReference pInt = new IntByReference(0);
        int dwState = hcNetSDK.NET_DVR_SendWithRecvRemoteConfig(lHandler, ptrByte.getPointer(), iStringSize, ptrOutuff.getPointer(), sdkPO.getHkfaceEnum().getSize(), pInt);
        //读取返回的json并解析
        ptrOutuff.read();
        String strResult = new String(ptrOutuff.byValue).trim();
        if (StringUtils.isNotBlank(strResult)) {
            log.info("dwState:" + dwState + ",strResult:" + strResult);
            HkFaceBaseVo tHkFaceBaseVo = JSONObject.parseObject(strResult, HkFaceBaseVo.class);
            tHkFaceBaseVo.setDwState(dwState);
            tHkFaceBaseVo.setStrResult(strResult);
            resultProcessing(sdkPO, dwState, tHkFaceBaseVo, lHandler);
            return tHkFaceBaseVo;
        }
        return null;
    }

    /**
     * 返回结果集处理
     *
     * @param sdkPO
     * @param dwState
     * @throws InterruptedException
     */
    private void resultProcessing(HkFaceBaseSdkPO sdkPO, int dwState, HkFaceBaseVo hkFaceBaseVo, int lHandler) throws InterruptedException {
        if (dwState == -1) {
            log.info("{} NET_DVR_SendWithRecvRemoteConfig接口调用失败，错误码: {}", sdkPO.getHkfaceEnum().getName(), hcNetSDK.NET_DVR_GetLastError());
            return;
        } else if (dwState == HCNetSDK.NET_SDK_CONFIG_STATUS_NEEDWAIT) {
            log.info("配置等待");
            Thread.sleep(10);
            return;
        } else if (dwState == HCNetSDK.NET_SDK_CONFIG_STATUS_FAILED) {
            log.info("{} 失败, json retun: {}", sdkPO.getHkfaceEnum().getName(), hkFaceBaseVo.toString());
            return;
        } else if (dwState == HCNetSDK.NET_SDK_CONFIG_STATUS_EXCEPTION) {
            hcNetSDK.NET_DVR_StopRemoteConfig(lHandler);
            log.info("{} 异常, json retun: {}", sdkPO.getHkfaceEnum().getName(), hkFaceBaseVo.toString());
            return;
        } else if (dwState == HCNetSDK.NET_SDK_CONFIG_STATUS_SUCCESS) {
            //返回NET_SDK_CONFIG_STATUS_SUCCESS代表流程走通了，但并不代表下发成功，比如有些设备可能因为人员已存在等原因下发失败，所以需要解析Json报文
            if (sdkPO.getHkfaceEnum().getIsFlag()) {
                if (hkFaceBaseVo.getStatusCode() != 1) {
                    log.info("{} 成功,但是有异常情况, json retun: {}", sdkPO.getHkfaceEnum().getName(), hkFaceBaseVo.toString());
                } else {
                    log.info("{} 成功, json retun: {}", sdkPO.getHkfaceEnum().getName(), hkFaceBaseVo.toString());
                }
            }
            return;
        } else if (dwState == HCNetSDK.NET_SDK_CONFIG_STATUS_FINISH) {
            //下发人员时：dwState其实不会走到这里，因为设备不知道我们会下发多少个人，所以长连接需要我们主动关闭
            log.info("{} 成功完成", sdkPO.getHkfaceEnum().getName());
            return;
        }
    }

}


