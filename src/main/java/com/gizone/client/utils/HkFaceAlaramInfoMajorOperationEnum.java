package com.gizone.client.utils;

import lombok.Getter;

/**
 * @author yyt
 */
@Getter
public enum HkFaceAlaramInfoMajorOperationEnum {
    /**
     * 海康人脸设备 报警信息详情 操作次类型
     */
    MINOR_LOCAL_LOGIN("MINOR_LOCAL_LOGIN","50","本地登陆","03"),
    MINOR_LOCAL_LOGOUT("MINOR_LOCAL_LOGOUT","51","本地注销登陆","03"),
    MINOR_LOCAL_UPGRADE("MINOR_LOCAL_UPGRADE","5a","本地升级","03"),
    MINOR_REMOTE_LOGIN("MINOR_REMOTE_LOGIN","70","远程登录","03"),
    MINOR_REMOTE_LOGOUT("MINOR_REMOTE_LOGOUT","71","远程注销登陆","03"),
    MINOR_REMOTE_ARM("MINOR_REMOTE_ARM","79","远程布防","03"),
    MINOR_REMOTE_DISARM("MINOR_REMOTE_DISARM","7a","远程撤防","03"),
    MINOR_REMOTE_REBOOT("MINOR_REMOTE_REBOOT","7b","远程重启","03"),
    MINOR_REMOTE_UPGRADE("MINOR_REMOTE_UPGRADE","7e","远程升级","03"),
    MINOR_REMOTE_CFGFILE_OUTPUT("MINOR_REMOTE_CFGFILE_OUTPUT","86","远程导出配置文件","03"),
    MINOR_REMOTE_CFGFILE_INTPUT("MINOR_REMOTE_CFGFILE_INTPUT","87","远程导入配置文件","03"),
    MINOR_REMOTE_ALARMOUT_OPEN_MAN("MINOR_REMOTE_ALARMOUT_OPEN_MAN","d6","远程手动开启报警输出","03"),
    MINOR_REMOTE_ALARMOUT_CLOSE_MAN("MINOR_REMOTE_ALARMOUT_CLOSE_MAN","d7","远程手动关闭报警输出","03"),
    MINOR_REMOTE_OPEN_DOOR("MINOR_REMOTE_OPEN_DOOR","400","远程开门","03"),
    MINOR_REMOTE_CLOSE_DOOR("MINOR_REMOTE_CLOSE_DOOR","401","远程关门（对于梯控，表示受控）","03"),
    MINOR_REMOTE_ALWAYS_OPEN("MINOR_REMOTE_ALWAYS_OPEN","402","远程常开（对于梯控，表示自由）","03"),
    MINOR_REMOTE_ALWAYS_CLOSE("MINOR_REMOTE_ALWAYS_CLOSE","403","远程常关（对于梯控，表示禁用）","03"),
    MINOR_REMOTE_CHECK_TIME("MINOR_REMOTE_CHECK_TIME","404","远程手动校时","03"),
    MINOR_NTP_CHECK_TIME("MINOR_NTP_CHECK_TIME","405","NTP自动校时","03"),
    MINOR_REMOTE_CLEAR_CARD("MINOR_REMOTE_CLEAR_CARD","406","远程清空卡号","03"),
    MINOR_REMOTE_RESTORE_CFG("MINOR_REMOTE_RESTORE_CFG","407","远程恢复默认参数","03"),
    MINOR_ALARMIN_ARM("MINOR_ALARMIN_ARM","408","防区布防","03"),
    MINOR_ALARMIN_DISARM("MINOR_ALARMIN_DISARM","409","防区撤防","03"),
    MINOR_LOCAL_RESTORE_CFG("MINOR_LOCAL_RESTORE_CFG","40a","本地恢复默认参数","03"),
    MINOR_REMOTE_CAPTURE_PIC("MINOR_REMOTE_CAPTURE_PIC","40b","远程抓拍","03"),
    MINOR_MOD_NET_REPORT_CFG("MINOR_MOD_NET_REPORT_CFG","40c","修改网络中心参数配置","03"),
    MINOR_MOD_GPRS_REPORT_PARAM("MINOR_MOD_GPRS_REPORT_PARAM","40d","修改GPRS中心参数配置","03"),
    MINOR_MOD_REPORT_GROUP_PARAM("MINOR_MOD_REPORT_GROUP_PARAM","40e","修改中心组参数配置","03"),
    MINOR_UNLOCK_PASSWORD_OPEN_DOOR("MINOR_UNLOCK_PASSWORD_OPEN_DOOR","40f","解除码输入","03"),
    MINOR_AUTO_RENUMBER("MINOR_AUTO_RENUMBER","410","自动重新编号","03"),
    MINOR_AUTO_COMPLEMENT_NUMBER("MINOR_AUTO_COMPLEMENT_NUMBER","411","自动补充编号","03"),
    MINOR_NORMAL_CFGFILE_INPUT("MINOR_NORMAL_CFGFILE_INPUT","412","导入普通配置文件","03"),
    MINOR_NORMAL_CFGFILE_OUTTPUT("MINOR_NORMAL_CFGFILE_OUTTPUT","413","导出普通配置文件","03"),
    MINOR_CARD_RIGHT_INPUT("MINOR_CARD_RIGHT_INPUT","414","导入卡权限参数","03"),
    MINOR_CARD_RIGHT_OUTTPUT("MINOR_CARD_RIGHT_OUTTPUT","415","导出卡权限参数","03"),
    MINOR_LOCAL_USB_UPGRADE("MINOR_LOCAL_USB_UPGRADE","416","本地U盘升级","03"),
    MINOR_REMOTE_VISITOR_CALL_LADDER("MINOR_REMOTE_VISITOR_CALL_LADDER","417","访客呼梯","03"),
    MINOR_REMOTE_HOUSEHOLD_CALL_LADDER("MINOR_REMOTE_HOUSEHOLD_CALL_LADDER","418","住户呼梯","03"),
    MINOR_REMOTE_ACTUAL_GUARD("MINOR_REMOTE_ACTUAL_GUARD","419","远程实时布防","03"),
    MINOR_REMOTE_ACTUAL_UNGUARD("MINOR_REMOTE_ACTUAL_UNGUARD","41a","远程实时撤防","03"),
    MINOR_REMOTE_CONTROL_NOT_CODE_OPER_FAILED("MINOR_REMOTE_CONTROL_NOT_CODE_OPER_FAILED","41b","遥控器未对码操作失败","03"),
    MINOR_REMOTE_CONTROL_CLOSE_DOOR("MINOR_REMOTE_CONTROL_CLOSE_DOOR","41c","遥控器关门","03"),
    MINOR_REMOTE_CONTROL_OPEN_DOOR("MINOR_REMOTE_CONTROL_OPEN_DOOR","41d","遥控器开门","03"),
    MINOR_REMOTE_CONTROL_ALWAYS_OPEN_DOOR("MINOR_REMOTE_CONTROL_ALWAYS_OPEN_DOOR","41e","遥控器常开门","03"),

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

    HkFaceAlaramInfoMajorOperationEnum(String definition, String value, String meaning, String mainValue) {
        this.definition = definition;
        this.value = value;
        this.meaning = meaning;
        this.mainValue = mainValue;
    }

    public static String getMeaning(String value) {
        for (HkFaceAlaramInfoMajorOperationEnum c : HkFaceAlaramInfoMajorOperationEnum.values()) {
            if (c.getValue().equals(value)) {
                return c.getMeaning();
            }
        }
        return null;
    }

}
