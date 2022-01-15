package com.gizone.client.constant;

/**
 * @author yyt
 */
public class HkFace {

    /**
     * 判断是不是jar启动关键字
     */
    public static String JAR_SPLIT_NAME = "/BOOT-INF/classes!";

    /**
     * 不是jar启动目录
     */
    public static String NOT_JAR_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\lib\\hk\\";

    /**
     * 加载 dll name
     */
    public static String DLL_NAME = "HCNetSDK.dll";

    /**
     * 临时文件目录
     */
    public static String TMP_DIR = System.getProperty("user.dir") + "\\tmp\\";

    /**
     * 临时文件标记
     */
    public static String TMP_TAG = "_hkfacetmp";
}
