package com.gizone.client.utils;

import lombok.Getter;

/**
 * @author yyt
 */
@Getter
public enum HkFaceAlaramInfoMajorAlarmEnum {
    /**
     * 海康人脸设备 报警信息详情 报警次类型
     */
    MINOR_ALARMIN_SHORT_CIRCUIT("MINOR_ALARMIN_SHORT_CIRCUIT", "400", "防区短路报警", "01"),
    MINOR_ALARMIN_BROKEN_CIRCUIT("MINOR_ALARMIN_BROKEN_CIRCUIT", "401", "防区断路报警", "01"),
    MINOR_ALARMIN_EXCEPTION("MINOR_ALARMIN_EXCEPTION", "402", "防区异常报警", "01"),
    MINOR_ALARMIN_RESUME("MINOR_ALARMIN_RESUME", "403", "防区报警恢复", "01"),
    MINOR_HOST_DESMANTLE_ALARM("MINOR_HOST_DESMANTLE_ALARM", "404", "设备防拆报警", "01"),
    MINOR_HOST_DESMANTLE_RESUME("MINOR_HOST_DESMANTLE_RESUME", "405", "设备防拆恢复", "01"),
    MINOR_CARD_READER_DESMANTLE_ALARM("MINOR_CARD_READER_DESMANTLE_ALARM", "406", "读卡器防拆报警", "01"),
    MINOR_CARD_READER_DESMANTLE_RESUME("MINOR_CARD_READER_DESMANTLE_RESUME", "407", "读卡器防拆恢复", "01"),
    MINOR_CASE_SENSOR_ALARM("MINOR_CASE_SENSOR_ALARM", "408", "事件输入报警", "01"),
    MINOR_CASE_SENSOR_RESUME("MINOR_CASE_SENSOR_RESUME", "409", "事件输入恢复", "01"),
    MINOR_STRESS_ALARM("MINOR_CASE_SENSOR_RESUME", "40a", "胁迫报警", "01"),
    MINOR_OFFLINE_ECENT_NEARLY_FULL("MINOR_OFFLINE_ECENT_NEARLY_FULL", "40b", "离线事件满90%报警", "01"),
    MINOR_CARD_MAX_AUTHENTICATE_FAIL("MINOR_CARD_MAX_AUTHENTICATE_FAIL", "40c", "卡号认证失败超次报警", "01"),
    MINOR_SD_CARD_FULL("MINOR_SD_CARD_FULL", "40d", "SD卡存储满报警", "01"),
    MINOR_LINKAGE_CAPTURE_PIC("MINOR_LINKAGE_CAPTURE_PIC", "40e", "联动抓拍事件报警", "01"),
    MINOR_SECURITY_MODULE_DESMANTLE_ALARM("MINOR_SECURITY_MODULE_DESMANTLE_ALARM", "40f", "门控安全模块防拆报警", "01"),
    MINOR_SECURITY_MODULE_DESMANTLE_RESUME("MINOR_SECURITY_MODULE_DESMANTLE_RESUME", "410", "门控安全模块防拆恢复", "01"),
    MINOR_POS_START_ALARM("MINOR_POS_START_ALARM", "411", "POS开启", "01"),
    MINOR_POS_END_ALARM("MINOR_POS_END_ALARM", "412", "POS结束", "01"),
    MINOR_FACE_IMAGE_QUALITY_LOW("MINOR_FACE_IMAGE_QUALITY_LOW", "413", "人脸图像画质低", "01"),
    MINOR_FINGE_RPRINT_QUALITY_LOW("MINOR_FINGE_RPRINT_QUALITY_LOW", "414", "指纹图像画质低", "01"),
    MINOR_FIRE_IMPORT_SHORT_CIRCUIT("MINOR_FIRE_IMPORT_SHORT_CIRCUIT", "415", "消防输入短路报警", "01"),
    MINOR_FIRE_IMPORT_BROKEN_CIRCUIT("MINOR_FIRE_IMPORT_BROKEN_CIRCUIT", "416", "消防输入断路报警", "01"),
    MINOR_FIRE_IMPORT_RESUME("MINOR_FIRE_IMPORT_RESUME", "417", "消防输入恢复", "01"),
    MINOR_FIRE_BUTTON_TRIGGER("MINOR_FIRE_BUTTON_TRIGGER", "418", "消防按钮触发", "01"),
    MINOR_FIRE_BUTTON_RESUME("MINOR_FIRE_BUTTON_RESUME", "419", "消防按钮恢复", "01"),
    MINOR_MAINTENANCE_BUTTON_TRIGGER("MINOR_MAINTENANCE_BUTTON_TRIGGER", "41a", "维护按钮触发", "01"),
    MINOR_MAINTENANCE_BUTTON_RESUME("MINOR_MAINTENANCE_BUTTON_RESUME", "41b", "维护按钮恢复", "01"),
    MINOR_EMERGENCY_BUTTON_TRIGGER("MINOR_EMERGENCY_BUTTON_TRIGGER", "41c", "紧急按钮触发", "01"),
    MINOR_EMERGENCY_BUTTON_RESUME("MINOR_EMERGENCY_BUTTON_RESUME", "41d", "紧急按钮恢复", "01"),
    MINOR_DISTRACT_CONTROLLER_ALARM("MINOR_DISTRACT_CONTROLLER_ALARM", "41e", "分控器防拆报警", "01"),
    MINOR_DISTRACT_CONTROLLER_RESUME("MINOR_DISTRACT_CONTROLLER_RESUME", "41f", "分控器防拆报警恢复", "01"),
    MINOR_CHANNEL_CONTROLLER_DESMANTLE_ALARM("MINOR_CHANNEL_CONTROLLER_DESMANTLE_ALARM", "422", "通道控制器防拆报警", "01"),
    MINOR_CHANNEL_CONTROLLER_DESMANTLE_RESUME("MINOR_CHANNEL_CONTROLLER_DESMANTLE_RESUME", "423", "通道控制器防拆报警恢复", "01"),
    MINOR_CHANNEL_CONTROLLER_FIRE_IMPORT_ALARM("MINOR_CHANNEL_CONTROLLER_FIRE_IMPORT_ALARM", "424", "通道控制器消防输入报警", "01"),
    MINOR_CHANNEL_CONTROLLER_FIRE_IMPORT_RESUME("MINOR_CHANNEL_CONTROLLER_FIRE_IMPORT_RESUME", "425", "通道控制器消防输入报警恢复", "01"),
    MINOR_LEGAL_EVENT_NEARLY_FULL("MINOR_LEGAL_EVENT_NEARLY_FULL", "442", "合法事件满90%报警", "01"),
    MINOR_ALARM_CUSTOM1("MINOR_LEGAL_EVENT_NEARLY_FULL", "900", "门禁自定义报警1", "01"),
    MINOR_LOCK_HIJIACK_ALARM("MINOR_LOCK_HIJIACK_ALARM", "95d", "智能锁防劫持报警", "01"),
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
    /**
     * 主类型定义 value
     */
    private String mainValue;

    HkFaceAlaramInfoMajorAlarmEnum(String definition, String value, String meaning, String mainValue) {
        this.definition = definition;
        this.value = value;
        this.meaning = meaning;
        this.mainValue = mainValue;
    }

    public static String getMeaning(String value) {
        for (HkFaceAlaramInfoMajorAlarmEnum c : HkFaceAlaramInfoMajorAlarmEnum.values()) {
            if (c.getValue().equals(value)) {
                return c.getMeaning();
            }
        }
        return null;
    }
}
