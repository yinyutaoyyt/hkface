package com.gizone.client.utils;

import java.io.*;
import java.net.URL;

/**
 * @author yyt
 */
public class FileUtil {

    //每次读取的大小 1KB
    public static final int BYTESIZE = 1024;

    public static final String TMP_URL = System.getProperty("user.dir") + "\\tmp\\";

    /**
     * @param is       待转化的文件流
     * @param fileName 临时文件名
     * @return
     * @throws IOException
     */
    public static File saveTempFile(InputStream is, String path, String fileName) {
        System.out.println(path);

        File temp = new File(path + fileName);

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            //把文件流转为文件，保存在临时目录
            bos = new BufferedOutputStream(new FileOutputStream(temp));
            int len = 0;
            //缓冲区
            byte[] buf = new byte[10 * BYTESIZE];
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }

            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) bos.close();
                if (bis != null) bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }

    /**
     * 将数据存入文件
     * @param fileName
     * @param data
     */
    public static void saveDataToFile(String fileName,String data) {
        BufferedWriter writer = null;
        File file = new File(fileName);
        //如果文件不存在，则新建一个
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //写入
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false), "UTF-8"));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将数据存入文件
     * @param fileName
     */
    public static String readDataToFile(String fileName) {
        String re = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String str;
            while ((str = in.readLine()) != null) {
                re = re + str;
            }
            return re;
        } catch (IOException e) {
        }
        return re;
    }

    public static File saveTempFile(String url, String path, String fileName) {

        InputStream inputStream = null;
        try {
            inputStream = new URL(url).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FileUtil.saveTempFile(inputStream, path, fileName);
    }

    /**
     * 删除文件  用来删除临时文件
     *
     * @param file
     */
    public static void deleteTempFile(File file) {
        file.delete();
    }
}
