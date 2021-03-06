package com.gizone.client.utils;

import lombok.Getter;

/**
 * @author yyt
 */
@Getter
public enum HkFaceAlaramInfoMajorEventEnum {
    /**
     * 海康人脸设备 报警信息详情 事件次类型
     */
    MINOR_LEGAL_CARD_PASS("MINOR_LEGAL_CARD_PASS", "01", "合法卡认证通过", "05"),
    MINOR_CARD_AND_PSW_PASS("MINOR_CARD_AND_PSW_PASS", "02", "刷卡加密码认证通过", "05"),
    MINOR_CARD_AND_PSW_FAIL("MINOR_CARD_AND_PSW_FAIL", "03", "刷卡加密码认证失败", "05"),
    MINOR_CARD_AND_PSW_TIMEOUT("MINOR_CARD_AND_PSW_TIMEOUT", "04", "数卡加密码认证超时", "05"),
    MINOR_CARD_AND_PSW_OVER_TIME("MINOR_CARD_AND_PSW_OVER_TIME", "05", "刷卡加密码超次", "05"),
    MINOR_CARD_NO_RIGHT("MINOR_CARD_NO_RIGHT", "06", "未分配权限", "05"),
    MINOR_CARD_INVALID_PERIOD("MINOR_CARD_INVALID_PERIOD", "07", "无效时段", "05"),
    MINOR_CARD_OUT_OF_DATE("MINOR_CARD_OUT_OF_DATE", "08", "卡号过期", "05"),
    MINOR_INVALID_CARD("MINOR_INVALID_CARD", "09", "无此卡号", "05"),
    MINOR_ANTI_SNEAK_FAIL("MINOR_ANTI_SNEAK_FAIL", "0a", "反潜回认证失败", "05"),
    MINOR_INTERLOCK_DOOR_NOT_CLOSE("MINOR_INTERLOCK_DOOR_NOT_CLOSE", "0b", "互锁门未关闭", "05"),
    MINOR_NOT_BELONG_MULTI_GROUP("MINOR_NOT_BELONG_MULTI_GROUP", "0c", "卡不属于多重认证群组", "05"),
    MINOR_INVALID_MULTI_VERIFY_PERIOD("MINOR_INVALID_MULTI_VERIFY_PERIOD", "0d", "卡不在多重认证时间段内", "05"),
    MINOR_MULTI_VERIFY_SUPER_RIGHT_FAIL("MINOR_MULTI_VERIFY_SUPER_RIGHT_FAIL", "0e", "多重认证模式超级权限认证失败", "05"),
    MINOR_MULTI_VERIFY_REMOTE_RIGHT_FAIL("MINOR_MULTI_VERIFY_REMOTE_RIGHT_FAIL", "0f", "多重认证模式远程认证失败", "05"),
    MINOR_MULTI_VERIFY_SUCCESS("MINOR_MULTI_VERIFY_SUCCESS", "10", "多重认证成功", "05"),
    MINOR_LEADER_CARD_OPEN_BEGIN("MINOR_LEADER_CARD_OPEN_BEGIN", "11", "首卡开门开始", "05"),
    MINOR_LEADER_CARD_OPEN_END("MINOR_LEADER_CARD_OPEN_END", "12", "首卡开门结束", "05"),
    MINOR_ALWAYS_OPEN_BEGIN("MINOR_ALWAYS_OPEN_BEGIN", "13", "常开状态开始", "05"),
    MINOR_ALWAYS_OPEN_END("MINOR_ALWAYS_OPEN_END", "14", "常开状态结束", "05"),
    MINOR_LOCK_OPEN("MINOR_LOCK_OPEN", "15", "门锁打开", "05"),
    MINOR_LOCK_CLOSE("MINOR_LOCK_CLOSE", "16", "门锁关闭", "05"),
    MINOR_DOOR_BUTTON_PRESS("MINOR_DOOR_BUTTON_PRESS", "17", "开门按钮打开", "05"),
    MINOR_DOOR_BUTTON_RELEASE("MINOR_DOOR_BUTTON_RELEASE", "18", "开门按钮放开", "05"),
    MINOR_DOOR_OPEN_NORMAL("MINOR_DOOR_OPEN_NORMAL", "19", "正常开门（门磁）", "05"),
    MINOR_DOOR_CLOSE_NORMAL("MINOR_DOOR_CLOSE_NORMAL", "1a", "正常关门（门磁）", "05"),
    MINOR_DOOR_OPEN_ABNORMAL("MINOR_DOOR_OPEN_ABNORMAL", "1b", "门异常打开（门磁）", "05"),
    MINOR_DOOR_OPEN_TIMEOUT("MINOR_DOOR_OPEN_TIMEOUT", "1c", "门打开超时（门磁）", "05"),
    MINOR_ALARMOUT_ON("MINOR_ALARMOUT_ON", "1d", "报警输出打开", "05"),
    MINOR_ALARMOUT_OFF("MINOR_ALARMOUT_OFF", "1e", "报警输出关闭", "05"),
    MINOR_ALWAYS_CLOSE_BEGIN("MINOR_ALWAYS_CLOSE_BEGIN", "1f", "常关状态开始", "05"),
    MINOR_ALWAYS_CLOSE_END("MINOR_ALWAYS_CLOSE_END", "20", "常关状态结束", "05"),
    MINOR_MULTI_VERIFY_NEED_REMOTE_OPEN("MINOR_MULTI_VERIFY_NEED_REMOTE_OPEN", "21", "多重多重认证需要远程开门", "05"),
    MINOR_MULTI_VERIFY_SUPERPASSWD_VERIFY_SUCCESS("MINOR_MULTI_VERIFY_SUPERPASSWD_VERIFY_SUCCESS", "22", "多重认证超级密码认证成功事件", "05"),
    MINOR_MULTI_VERIFY_REPEAT_VERIFY("MINOR_MULTI_VERIFY_REPEAT_VERIFY", "23", "多重认证重复认证事件", "05"),
    MINOR_MULTI_VERIFY_TIMEOUT("MINOR_MULTI_VERIFY_TIMEOUT", "24", "多重认证重复认证事件", "05"),
    MINOR_DOORBELL_RINGING("MINOR_DOORBELL_RINGING", "25", "门铃响", "05"),
    MINOR_FINGERPRINT_COMPARE_PASS("MINOR_FINGERPRINT_COMPARE_PASS", "26", "指纹比对通过", "05"),
    MINOR_FINGERPRINT_COMPARE_FAIL("MINOR_FINGERPRINT_COMPARE_FAIL", "27", "指纹比对失败", "05"),
    MINOR_CARD_FINGERPRINT_VERIFY_PASS("MINOR_CARD_FINGERPRINT_VERIFY_PASS", "28", "刷卡加指纹认证通过", "05"),
    MINOR_CARD_FINGERPRINT_VERIFY_FAIL("MINOR_CARD_FINGERPRINT_VERIFY_FAIL", "29", "刷卡加指纹认证失败", "05"),
    MINOR_CARD_FINGERPRINT_VERIFY_TIMEOUT("MINOR_CARD_FINGERPRINT_VERIFY_TIMEOUT", "2a", "刷卡加指纹认证超时", "05"),
    MINOR_CARD_FINGERPRINT_PASSWD_VERIFY_PASS("MINOR_CARD_FINGERPRINT_PASSWD_VERIFY_PASS", "2b", "刷卡加指纹加密码认证通过", "05"),
    MINOR_CARD_FINGERPRINT_PASSWD_VERIFY_FAIL("MINOR_CARD_FINGERPRINT_PASSWD_VERIFY_FAIL", "2c", "刷卡加指纹加密码认证失败", "05"),
    MINOR_CARD_FINGERPRINT_PASSWD_VERIFY_TIMEOUT("MINOR_CARD_FINGERPRINT_PASSWD_VERIFY_TIMEOUT", "2d", "刷卡加指纹加密码认证超时", "05"),
    MINOR_FINGERPRINT_PASSWD_VERIFY_PASS("MINOR_FINGERPRINT_PASSWD_VERIFY_PASS", "2e", "指纹加密码认证通过", "05"),
    MINOR_FINGERPRINT_PASSWD_VERIFY_FAIL("MINOR_FINGERPRINT_PASSWD_VERIFY_FAIL", "2f", "指纹加密码认证失败", "05"),
    MINOR_FINGERPRINT_PASSWD_VERIFY_TIMEOUT("MINOR_FINGERPRINT_PASSWD_VERIFY_TIMEOUT", "30", "指纹加密码认证超时", "05"),
    MINOR_FINGERPRINT_INEXISTENCE("MINOR_FINGERPRINT_INEXISTENCE", "31", "指纹不存在", "05"),
    MINOR_CARD_PLATFORM_VERIFY("MINOR_CARD_PLATFORM_VERIFY", "32", "刷卡平台认证", "05"),
    MINOR_CALL_CENTER("MINOR_CALL_CENTER", "33", "呼叫中心事件", "05"),
    MINOR_FIRE_RELAY_TURN_ON_DOOR_ALWAYS_OPEN("MINOR_FIRE_RELAY_TURN_ON_DOOR_ALWAYS_OPEN", "34", "消防继电器导通触发门常开", "05"),
    MINOR_FIRE_RELAY_RECOVER_DOOR_RECOVER_NORMAL("MINOR_FIRE_RELAY_RECOVER_DOOR_RECOVER_NORMAL", "35", "消防继电器恢复门恢复正常", "05"),
    MINOR_FACE_AND_FP_VERIFY_PASS("MINOR_FACE_AND_FP_VERIFY_PASS", "36", "人脸加指纹认证通过", "05"),
    MINOR_FACE_AND_FP_VERIFY_FAIL("MINOR_FACE_AND_FP_VERIFY_FAIL", "37", "人脸加指纹认证失败", "05"),
    MINOR_FACE_AND_FP_VERIFY_TIMEOUT("MINOR_FACE_AND_FP_VERIFY_TIMEOUT", "38", "人脸加指纹认证超时", "05"),
    MINOR_FACE_AND_PW_VERIFY_PASS("MINOR_FACE_AND_PW_VERIFY_PASS", "39", "人脸加密码认证通过", "05"),
    MINOR_FACE_AND_PW_VERIFY_FAIL("MINOR_FACE_AND_PW_VERIFY_FAIL", "3a", "人脸加密码认证失败", "05"),
    MINOR_FACE_AND_PW_VERIFY_TIMEOUT("MINOR_FACE_AND_PW_VERIFY_TIMEOUT", "3b", "人脸加密码认证超时", "05"),
    MINOR_FACE_AND_CARD_VERIFY_PASS("MINOR_FACE_AND_CARD_VERIFY_PASS", "3c", "人脸加刷卡认证通过", "05"),
    MINOR_FACE_AND_CARD_VERIFY_FAIL("MINOR_FACE_AND_CARD_VERIFY_FAIL", "3d", "人脸加刷卡认证失败", "05"),
    MINOR_FACE_AND_CARD_VERIFY_TIMEOUT("MINOR_FACE_AND_CARD_VERIFY_TIMEOUT", "3e", "人脸加刷卡认证超时", "05"),
    MINOR_FACE_AND_PW_AND_FP_VERIFY_PASS("MINOR_FACE_AND_PW_AND_FP_VERIFY_PASS", "3f", "人脸加密码加指纹认证通过", "05"),
    MINOR_FACE_AND_PW_AND_FP_VERIFY_FAIL("MINOR_FACE_AND_PW_AND_FP_VERIFY_FAIL", "40", "人脸加密码加指纹认证失败", "05"),
    MINOR_FACE_AND_PW_AND_FP_VERIFY_TIMEOUT("MINOR_FACE_AND_PW_AND_FP_VERIFY_TIMEOUT", "41", "人脸加密码加指纹认证超时", "05"),
    MINOR_FACE_CARD_AND_FP_VERIFY_PASS("MINOR_FACE_CARD_AND_FP_VERIFY_PASS", "42", "人脸加刷卡加指纹认证通过", "05"),
    MINOR_FACE_CARD_AND_FP_VERIFY_FAIL("MINOR_FACE_CARD_AND_FP_VERIFY_FAIL", "43", "人脸加刷卡加指纹认证失败", "05"),
    MINOR_FACE_CARD_AND_FP_VERIFY_TIMEOUT("MINOR_FACE_CARD_AND_FP_VERIFY_TIMEOUT", "44", "人脸加刷卡加指纹认证超时", "05"),
    MINOR_EMPLOYEENO_AND_FP_VERIFY_PASS("MINOR_EMPLOYEENO_AND_FP_VERIFY_PASS", "45", "工号加指纹认证通过", "05"),
    MINOR_EMPLOYEENO_AND_FP_VERIFY_FAIL("MINOR_EMPLOYEENO_AND_FP_VERIFY_FAIL", "46", "工号加指纹认证失败", "05"),
    MINOR_EMPLOYEENO_AND_FP_VERIFY_TIMEOUT("MINOR_EMPLOYEENO_AND_FP_VERIFY_TIMEOUT", "47", "工号加指纹认证超时", "05"),
    MINOR_EMPLOYEENO_AND_FP_AND_PW_VERIFY_PASS("MINOR_EMPLOYEENO_AND_FP_AND_PW_VERIFY_PASS", "48", "工号加指纹加密码认证通过", "05"),
    MINOR_EMPLOYEENO_AND_FP_AND_PW_VERIFY_FAIL("MINOR_EMPLOYEENO_AND_FP_AND_PW_VERIFY_FAIL", "49", "工号加指纹加密码认证失败", "05"),
    MINOR_EMPLOYEENO_AND_FP_AND_PW_VERIFY_TIMEOUT("MINOR_EMPLOYEENO_AND_FP_AND_PW_VERIFY_TIMEOUT", "4a", "工号加指纹加密码认证超时", "05"),
    MINOR_FACE_VERIFY_PASS("MINOR_FACE_VERIFY_PASS", "4b", "人脸认证通过", "05"),
    MINOR_FACE_VERIFY_FAIL("MINOR_FACE_VERIFY_FAIL", "4c", "人脸认证失败", "05"),
    MINOR_EMPLOYEENO_AND_FACE_VERIFY_PASS("MINOR_EMPLOYEENO_AND_FACE_VERIFY_PASS", "4d", "工号加人脸认证通过", "05"),
    MINOR_EMPLOYEENO_AND_FACE_VERIFY_FAIL("MINOR_EMPLOYEENO_AND_FACE_VERIFY_FAIL", "4e", "工号加人脸认证失败", "05"),
    MINOR_EMPLOYEENO_AND_FACE_VERIFY_TIMEOUT("MINOR_EMPLOYEENO_AND_FACE_VERIFY_TIMEOUT", "4f", "工号加人脸认证超时", "05"),
    MINOR_FACE_RECOGNIZE_FAIL("MINOR_FACE_RECOGNIZE_FAIL", "50", "人脸识别失败", "05"),
    MINOR_FIRSTCARD_AUTHORIZE_BEGIN("MINOR_FIRSTCARD_AUTHORIZE_BEGIN", "51", "首卡授权开始", "05"),
    MINOR_FIRSTCARD_AUTHORIZE_END("MINOR_FIRSTCARD_AUTHORIZE_END", "52", "首卡授权结束", "05"),
    MINOR_DOORLOCK_INPUT_SHORT_CIRCUIT("MINOR_DOORLOCK_INPUT_SHORT_CIRCUIT", "53", "门锁输入短路报警", "05"),
    MINOR_DOORLOCK_INPUT_BROKEN_CIRCUIT("MINOR_DOORLOCK_INPUT_BROKEN_CIRCUIT", "54", "门锁输入断路报警", "05"),
    MINOR_DOORLOCK_INPUT_EXCEPTION("MINOR_DOORLOCK_INPUT_EXCEPTION", "55", "门锁输入异常报警", "05"),
    MINOR_DOORCONTACT_INPUT_SHORT_CIRCUIT("MINOR_DOORCONTACT_INPUT_SHORT_CIRCUIT", "56", "门磁输入短路报警", "05"),
    MINOR_DOORCONTACT_INPUT_BROKEN_CIRCUIT("MINOR_DOORCONTACT_INPUT_BROKEN_CIRCUIT", "57", "门磁输入断路报警", "05"),
    MINOR_DOORCONTACT_INPUT_EXCEPTION("MINOR_DOORCONTACT_INPUT_EXCEPTION", "58", "门磁输入异常报警", "05"),
    MINOR_OPENBUTTON_INPUT_SHORT_CIRCUIT("MINOR_OPENBUTTON_INPUT_SHORT_CIRCUIT", "59", "开门按钮输入短路报警", "05"),
    MINOR_OPENBUTTON_INPUT_BROKEN_CIRCUIT("MINOR_OPENBUTTON_INPUT_BROKEN_CIRCUIT", "5a", "开门按钮输入断路报警", "05"),
    MINOR_OPENBUTTON_INPUT_EXCEPTION("MINOR_OPENBUTTON_INPUT_EXCEPTION", "5b", "开门按钮输入异常报警", "05"),
    MINOR_DOORLOCK_OPEN_EXCEPTION("MINOR_DOORLOCK_OPEN_EXCEPTION", "5c", "门锁异常打开", "05"),
    MINOR_DOORLOCK_OPEN_TIMEOUT("MINOR_DOORLOCK_OPEN_TIMEOUT", "5d", "门锁打开超时", "05"),
    MINOR_FIRSTCARD_OPEN_WITHOUT_AUTHORIZE("MINOR_FIRSTCARD_OPEN_WITHOUT_AUTHORIZE", "5e", "首卡未授权开门失败", "05"),
    MINOR_CALL_LADDER_RELAY_BREAK("MINOR_CALL_LADDER_RELAY_BREAK", "5f", "呼梯继电器断开", "05"),
    MINOR_CALL_LADDER_RELAY_CLOSE("MINOR_CALL_LADDER_RELAY_CLOSE", "60", "呼梯继电器闭合", "05"),
    MINOR_AUTO_KEY_RELAY_BREAK("MINOR_AUTO_KEY_RELAY_BREAK", "61", "自动按键继电器断开", "05"),
    MINOR_AUTO_KEY_RELAY_CLOSE("MINOR_AUTO_KEY_RELAY_CLOSE", "62", "自动按键继电器闭合", "05"),
    MINOR_KEY_CONTROL_RELAY_BREAK("MINOR_KEY_CONTROL_RELAY_BREAK", "63", "按键梯控继电器断开", "05"),
    MINOR_KEY_CONTROL_RELAY_CLOSE("MINOR_KEY_CONTROL_RELAY_CLOSE", "64", "按键梯控继电器闭合", "05"),
    MINOR_EMPLOYEENO_AND_PW_PASS("MINOR_EMPLOYEENO_AND_PW_PASS", "65", "工号加密码认证通过", "05"),
    MINOR_EMPLOYEENO_AND_PW_FAIL("MINOR_EMPLOYEENO_AND_PW_FAIL", "66", "工号加密码认证失败", "05"),
    MINOR_EMPLOYEENO_AND_PW_TIMEOUT("MINOR_EMPLOYEENO_AND_PW_TIMEOUT", "67", "工号加密码认证超时", "05"),
    MINOR_HUMAN_DETECT_FAIL("MINOR_HUMAN_DETECT_FAIL", "68", "真人检测失败", "05"),
    MINOR_PEOPLE_AND_ID_CARD_COMPARE_PASS("MINOR_PEOPLE_AND_ID_CARD_COMPARE_PASS", "69", "人证比对通过", "05"),
    MINOR_PEOPLE_AND_ID_CARD_COMPARE_FAIL("MINOR_PEOPLE_AND_ID_CARD_COMPARE_FAIL", "70", "人证比对失败", "05"),
    MINOR_CERTIFICATE_BLOCKLIST("MINOR_CERTIFICATE_BLOCKLIST", "71", "黑名单事件", "05"),
    MINOR_LEGAL_MESSAGE("MINOR_LEGAL_MESSAGE", "72", "合法短信", "05"),
    MINOR_ILLEGAL_MESSAGE("MINOR_ILLEGAL_MESSAGE", "73", "非法短信", "05"),
    MINOR_MAC_DETECT("MINOR_MAC_DETECT", "74", "MAC侦测", "05"),
    MINOR_DOOR_OPEN_OR_DORMANT_FAIL("MINOR_DOOR_OPEN_OR_DORMANT_FAIL", "75", "门状态常闭或休眠状态认证失败", "05"),
    MINOR_AUTH_PLAN_DORMANT_FAIL("MINOR_AUTH_PLAN_DORMANT_FAIL", "76", "认证计划休眠模式认证失败", "05"),
    MINOR_CARD_ENCRYPT_VERIFY_FAIL("MINOR_CARD_ENCRYPT_VERIFY_FAIL", "77", "卡加密校验失败", "05"),
    MINOR_SUBMARINEBACK_REPLY_FAIL("MINOR_SUBMARINEBACK_REPLY_FAIL", "78", "反潜回服务器应答失败", "05"),
    MINOR_TRAILING("MINOR_TRAILING", "85", "尾随通行", "05"),
    MINOR_REVERSE_ACCESS("MINOR_REVERSE_ACCESS", "86", "反向闯入", "05"),
    MINOR_FORCE_ACCESS("MINOR_FORCE_ACCESS", "87", "外力冲撞", "05"),
    MINOR_CLIMBING_OVER_GATE("MINOR_CLIMBING_OVER_GATE", "88", "翻越", "05"),
    MINOR_PASSING_TIMEOUT("MINOR_PASSING_TIMEOUT", "89", "通行超时", "05"),
    MINOR_INTRUSION_ALARM("MINOR_INTRUSION_ALARM", "8a", "误闯报警", "05"),
    MINOR_FREE_GATE_PASS_NOT_AUTH("MINOR_FREE_GATE_PASS_NOT_AUTH", "8b", "闸机自由通行时未认证通过", "05"),
    MINOR_DROP_ARM_BLOCK("MINOR_DROP_ARM_BLOCK", "8c", "摆臂被阻挡", "05"),
    MINOR_DROP_ARM_BLOCK_RESUME("MINOR_DROP_ARM_BLOCK_RESUME", "8d", "摆臂阻挡消除", "05"),
    MINOR_LOCAL_FACE_MODELING_FAIL("MINOR_LOCAL_FACE_MODELING_FAIL", "8e", "设备升级本地人脸建模失败", "05"),
    MINOR_STAY_EVENT("MINOR_STAY_EVENT", "8f", "逗留事件", "05"),
    MINOR_PASSWORD_MISMATCH("MINOR_PASSWORD_MISMATCH", "97", "密码不匹配", "05"),
    MINOR_EMPLOYEE_NO_NOT_EXIST("MINOR_EMPLOYEE_NO_NOT_EXIST", "98", "工号不存在", "05"),
    MINOR_COMBINED_VERIFY_PASS("MINOR_COMBINED_VERIFY_PASS", "99", "组合认证通过", "05"),
    MINOR_COMBINED_VERIFY_TIMEOUT("MINOR_COMBINED_VERIFY_TIMEOUT", "9a", "组合认证超时", "05"),
    MINOR_VERIFY_MODE_MISMATCH("MINOR_VERIFY_MODE_MISMATCH", "9b", "认证方式不匹配", "05"),
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

    HkFaceAlaramInfoMajorEventEnum(String definition, String value, String meaning, String mainValue) {
        this.definition = definition;
        this.value = value;
        this.meaning = meaning;
        this.mainValue = mainValue;
    }

    public static String getMeaning(String value) {
        for (HkFaceAlaramInfoMajorEventEnum c : HkFaceAlaramInfoMajorEventEnum.values()) {
            if (c.getValue().equals(value)) {
                return c.getMeaning();
            }
        }
        return null;
    }
}
