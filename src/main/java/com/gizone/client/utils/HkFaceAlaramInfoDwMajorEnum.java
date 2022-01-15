package com.gizone.client.utils;

import lombok.Getter;

/**
 * @author yyt
 */
@Getter
public enum HkFaceAlaramInfoDwMajorEnum {
    /**
     * 海康人脸设备 报警信息详情 主报警类型
     */
    MAJOR_ALARM("MAJOR_ALARM", "01", "报警"),
    MAJOR_EXCEPTION("MAJOR_EXCEPTION", "02", "异常"),
    MAJOR_OPERATION("MAJOR_OPERATION", "03", "操作"),
    MAJOR_EVENT("MAJOR_EXCEPTION", "05", "事件"),
    ;
    /**
     * 定义
     */
    private String definition;
    /**
     * 16进制
     */
    private String value;
    /**
     * 含义
     */
    private String meaning;

    HkFaceAlaramInfoDwMajorEnum(String definition, String value, String meaning) {
        this.definition = definition;
        this.value = value;
        this.meaning = meaning;
    }

    public static String getMeaning(String value) {
        for (HkFaceAlaramInfoDwMajorEnum c : HkFaceAlaramInfoDwMajorEnum.values()) {
            if (c.getValue().equals(value)) {
                return c.getMeaning();
            }
        }
        return null;
    }

    public static String getChildMeaning(String value, String childValue) {
        for (HkFaceAlaramInfoDwMajorEnum c : HkFaceAlaramInfoDwMajorEnum.values()) {
            if (HkFaceAlaramInfoDwMajorEnum.MAJOR_ALARM.getValue().equals(value)) {
                return HkFaceAlaramInfoMajorAlarmEnum.getMeaning(childValue);
            }
            if (HkFaceAlaramInfoDwMajorEnum.MAJOR_EXCEPTION.getValue().equals(value)) {
                return HkFaceAlaramInfoMajorExceptionEnum.getMeaning(childValue);
            }
            if (HkFaceAlaramInfoDwMajorEnum.MAJOR_OPERATION.getValue().equals(value)) {
                return HkFaceAlaramInfoMajorOperationEnum.getMeaning(childValue);
            }
            if (HkFaceAlaramInfoDwMajorEnum.MAJOR_EVENT.getValue().equals(value)) {
                return HkFaceAlaramInfoMajorEventEnum.getMeaning(childValue);
            }
        }
        return null;
    }
}
