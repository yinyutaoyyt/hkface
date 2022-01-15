package com.gizone.client.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author yyt
 */
public class Constant {

    /**
     * 判断道闸是海康还是adk
     */
    public static String IS_HK_OR_ADK = "parkCode";

    /**
     * 根据停车场编号获取停车场id
     */
    public static String PARKING_ID_BY_NO = "parkingIdByNo:";

    /**
     * 根据车道编号获取车道id
     */
    public static String LANE_ID_BY_NO = "laneIdByNo:";

    /**
     * redis 默认失效时间
     */
    public static Long REDIS_DEFAULT_EXPIRE_TIME = 60 * 60 * 24L;

    /**
     * 未删除状态
     */
    public static Integer IS_NOT_DELETE = 1;

    /**
     * status 为 1
     */
    public static Integer STATUS_ONE = 1;

    /**
     * 小时
     */
    public static String FORMAT_HOUR = "小时";

    /**
     * 小时
     */
    public static String FORMAT_MIN = "分钟";

    /**
     * 预约默认权限
     */
    public static String APPOINTMENT_DEFAULT_PARK_AUTH = "1,2";

    /**
     * 预约默认车辆属性
     */
    public static Integer APPOINTMENT_DEFAULT_PARKING_TYPE = 0;

    /**
     * 预约临时车卡有效天数
     */
    public static Integer APPOINTMENT_TEMPORARY_BLACE_DAY = 1;

    /**
     * 不同步的车牌号
     */
    public static List NOT_SYNC_PLATE_NO = new ArrayList(){{
        add("无车牌");
    }};

    /**
     * 判断字符串是否为纯数字
     *是整数返回true,否则返回false
     */
    public static Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
    public static boolean pattern(String str) {
        return pattern.matcher(str).matches();
    }

}
