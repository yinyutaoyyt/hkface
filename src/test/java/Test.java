import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {

        //转为枚举
        String str = "MINOR_LEGAL_CARD_PASS 0x01 合法卡认证通过 \n" +
                "MINOR_CARD_AND_PSW_PASS 0x02 刷卡加密码认证通过 \n" +
                "MINOR_CARD_AND_PSW_FAIL 0x03 刷卡加密码认证失败 \n" +
                "MINOR_CARD_AND_PSW_TIMEOUT 0x04 数卡加密码认证超时 \n" +
                "MINOR_CARD_AND_PSW_OVER_TIME 0x05 刷卡加密码超次 \n" +
                "MINOR_CARD_NO_RIGHT 0x06 未分配权限 \n" +
                "MINOR_CARD_INVALID_PERIOD 0x07 无效时段 \n" +
                "MINOR_CARD_OUT_OF_DATE 0x08 卡号过期 \n" +
                "MINOR_INVALID_CARD 0x09 无此卡号 \n" +
                "MINOR_ANTI_SNEAK_FAIL 0x0a 反潜回认证失败 \n" +
                "MINOR_INTERLOCK_DOOR_NOT_CLOSE 0x0b 互锁门未关闭 \n" +
                "MINOR_NOT_BELONG_MULTI_GROUP 0x0c 卡不属于多重认证群组 \n" +
                "MINOR_INVALID_MULTI_VERIFY_PERIOD 0x0d 卡不在多重认证时间段内 \n" +
                "MINOR_MULTI_VERIFY_SUPER_RIGHT_FAIL 0x0e 多重认证模式超级权限认证失败 \n" +
                "MINOR_MULTI_VERIFY_REMOTE_RIGHT_FAIL 0x0f 多重认证模式远程认证失败 \n" +
                "MINOR_MULTI_VERIFY_SUCCESS 0x10 多重认证成功 \n" +
                "MINOR_LEADER_CARD_OPEN_BEGIN 0x11 首卡开门开始 \n" +
                "MINOR_LEADER_CARD_OPEN_END 0x12 首卡开门结束 \n" +
                "MINOR_ALWAYS_OPEN_BEGIN 0x13 常开状态开始 \n" +
                "MINOR_ALWAYS_OPEN_END 0x14 常开状态结束 \n" +
                "MINOR_LOCK_OPEN 0x15 门锁打开 \n" +
                "MINOR_LOCK_CLOSE 0x16 门锁关闭 \n" +
                "MINOR_DOOR_BUTTON_PRESS 0x17 开门按钮打开 \n" +
                "MINOR_DOOR_BUTTON_RELEASE 0x18 开门按钮放开 \n" +
                "MINOR_DOOR_OPEN_NORMAL 0x19 正常开门（门磁） \n" +
                "MINOR_DOOR_CLOSE_NORMAL 0x1a 正常关门（门磁） \n" +
                "MINOR_DOOR_OPEN_ABNORMAL 0x1b 门异常打开（门磁） \n" +
                "MINOR_DOOR_OPEN_TIMEOUT 0x1c 门打开超时（门磁）  \n" +
                "MINOR_ALARMOUT_ON 0x1d 报警输出打开 \n" +
                "MINOR_ALARMOUT_OFF 0x1e 报警输出关闭 \n" +
                "MINOR_ALWAYS_CLOSE_BEGIN 0x1f 常关状态开始 \n" +
                "MINOR_ALWAYS_CLOSE_END 0x20 常关状态结束 \n" +
                "MINOR_MULTI_VERIFY_NEED_REMOTE_OPEN 0x21 多重多重认证需要远程开门 \n" +
                "MINOR_MULTI_VERIFY_SUPERPASSWD_VERIFY_SUCCESS 0x22 多重认证超级密码认证成功事件 \n" +
                "MINOR_MULTI_VERIFY_REPEAT_VERIFY 0x23 多重认证重复认证事件 \n" +
                "MINOR_MULTI_VERIFY_TIMEOUT 0x24 多重认证重复认证事件 \n" +
                "MINOR_DOORBELL_RINGING 0x25 门铃响 \n" +
                "MINOR_FINGERPRINT_COMPARE_PASS 0x26 指纹比对通过 \n" +
                "MINOR_FINGERPRINT_COMPARE_FAIL 0x27 指纹比对失败 \n" +
                "MINOR_CARD_FINGERPRINT_VERIFY_PASS 0x28 刷卡加指纹认证通过 \n" +
                "MINOR_CARD_FINGERPRINT_VERIFY_FAIL 0x29 刷卡加指纹认证失败 \n" +
                "MINOR_CARD_FINGERPRINT_VERIFY_TIMEOUT 0x2a 刷卡加指纹认证超时 \n" +
                "MINOR_CARD_FINGERPRINT_PASSWD_VERIFY_PASS 0x2b 刷卡加指纹加密码认证通过 \n" +
                "MINOR_CARD_FINGERPRINT_PASSWD_VERIFY_FAIL 0x2c 刷卡加指纹加密码认证失败 \n" +
                "MINOR_CARD_FINGERPRINT_PASSWD_VERIFY_TIMEOUT 0x2d 刷卡加指纹加密码认证超时 \n" +
                "MINOR_FINGERPRINT_PASSWD_VERIFY_PASS 0x2e 指纹加密码认证通过 \n" +
                "MINOR_FINGERPRINT_PASSWD_VERIFY_FAIL 0x2f 指纹加密码认证失败 \n" +
                "MINOR_FINGERPRINT_PASSWD_VERIFY_TIMEOUT 0x30 指纹加密码认证超时 \n" +
                "MINOR_FINGERPRINT_INEXISTENCE 0x31 指纹不存在 \n" +
                "MINOR_CARD_PLATFORM_VERIFY 0x32 刷卡平台认证 \n" +
                "MINOR_CALL_CENTER 0x33 呼叫中心事件 \n" +
                "MINOR_FIRE_RELAY_TURN_ON_DOOR_ALWAYS_OPEN 0x34 消防继电器导通触发门常开 \n" +
                "MINOR_FIRE_RELAY_RECOVER_DOOR_RECOVER_NORMAL 0x35 消防继电器恢复门恢复正常 \n" +
                "MINOR_FACE_AND_FP_VERIFY_PASS 0x36 人脸加指纹认证通过 \n" +
                "MINOR_FACE_AND_FP_VERIFY_FAIL  0x37 人脸加指纹认证失败 \n" +
                "MINOR_FACE_AND_FP_VERIFY_TIMEOUT 0x38 人脸加指纹认证超时 \n" +
                "MINOR_FACE_AND_PW_VERIFY_PASS 0x39 人脸加密码认证通过 \n" +
                "MINOR_FACE_AND_PW_VERIFY_FAIL 0x3a 人脸加密码认证失败 \n" +
                "MINOR_FACE_AND_PW_VERIFY_TIMEOUT 0x3b 人脸加密码认证超时 \n" +
                "MINOR_FACE_AND_CARD_VERIFY_PASS 0x3c 人脸加刷卡认证通过 \n" +
                "MINOR_FACE_AND_CARD_VERIFY_FAIL 0x3d 人脸加刷卡认证失败 \n" +
                "MINOR_FACE_AND_CARD_VERIFY_TIMEOUT 0x3e 人脸加刷卡认证超时 \n" +
                "MINOR_FACE_AND_PW_AND_FP_VERIFY_PASS 0x3f 人脸加密码加指纹认证通过 \n" +
                "MINOR_FACE_AND_PW_AND_FP_VERIFY_FAIL 0x40 人脸加密码加指纹认证失败 \n" +
                "MINOR_FACE_AND_PW_AND_FP_VERIFY_TIMEOUT 0x41 人脸加密码加指纹认证超时 \n" +
                "MINOR_FACE_CARD_AND_FP_VERIFY_PASS 0x42 人脸加刷卡加指纹认证通过 \n" +
                "MINOR_FACE_CARD_AND_FP_VERIFY_FAIL 0x43 人脸加刷卡加指纹认证失败 \n" +
                "MINOR_FACE_CARD_AND_FP_VERIFY_TIMEOUT 0x44 人脸加刷卡加指纹认证超时 \n" +
                "MINOR_EMPLOYEENO_AND_FP_VERIFY_PASS 0x45 工号加指纹认证通过 \n" +
                "MINOR_EMPLOYEENO_AND_FP_VERIFY_FAIL 0x46 工号加指纹认证失败 \n" +
                "MINOR_EMPLOYEENO_AND_FP_VERIFY_TIMEOUT 0x47 工号加指纹认证超时 \n" +
                "MINOR_EMPLOYEENO_AND_FP_AND_PW_VERIFY_PASS 0x48 工号加指纹加密码认证通过 \n" +
                "MINOR_EMPLOYEENO_AND_FP_AND_PW_VERIFY_FAIL 0x49 工号加指纹加密码认证失败 \n" +
                "MINOR_EMPLOYEENO_AND_FP_AND_PW_VERIFY_TIMEOUT 0x4a 工号加指纹加密码认证超时 \n" +
                "MINOR_FACE_VERIFY_PASS 0x4b 人脸认证通过 \n" +
                "MINOR_FACE_VERIFY_FAIL 0x4c 人脸认证失败 \n" +
                "MINOR_EMPLOYEENO_AND_FACE_VERIFY_PASS 0x4d 工号加人脸认证通过 \n" +
                "MINOR_EMPLOYEENO_AND_FACE_VERIFY_FAIL 0x4e 工号加人脸认证失败 \n" +
                "MINOR_EMPLOYEENO_AND_FACE_VERIFY_TIMEOUT 0x4f 工号加人脸认证超时 \n" +
                "MINOR_FACE_RECOGNIZE_FAIL 0x50 人脸识别失败 \n" +
                "MINOR_FIRSTCARD_AUTHORIZE_BEGIN  0x51 首卡授权开始 \n" +
                "MINOR_FIRSTCARD_AUTHORIZE_END  0x52 首卡授权结束 \n" +
                "MINOR_DOORLOCK_INPUT_SHORT_CIRCUIT 0x53 门锁输入短路报警 \n" +
                "MINOR_DOORLOCK_INPUT_BROKEN_CIRCUIT 0x54 门锁输入断路报警 \n" +
                "MINOR_DOORLOCK_INPUT_EXCEPTION 0x55 门锁输入异常报警 \n" +
                "MINOR_DOORCONTACT_INPUT_SHORT_CIRCUIT 0x56 门磁输入短路报警 \n" +
                "MINOR_DOORCONTACT_INPUT_BROKEN_CIRCUIT 0x57 门磁输入断路报警 \n" +
                "MINOR_DOORCONTACT_INPUT_EXCEPTION 0x58 门磁输入异常报警 \n" +
                "MINOR_OPENBUTTON_INPUT_SHORT_CIRCUIT  0x59 开门按钮输入短路报警 \n" +
                "MINOR_OPENBUTTON_INPUT_BROKEN_CIRCUIT  0x5a 开门按钮输入断路报警 \n" +
                "MINOR_OPENBUTTON_INPUT_EXCEPTION 0x5b 开门按钮输入异常报警 \n" +
                "MINOR_DOORLOCK_OPEN_EXCEPTION 0x5c 门锁异常打开 \n" +
                "MINOR_DOORLOCK_OPEN_TIMEOUT 0x5d 门锁打开超时 \n" +
                "MINOR_FIRSTCARD_OPEN_WITHOUT_AUTHORIZE 0x5e 首卡未授权开门失败 \n" +
                "MINOR_CALL_LADDER_RELAY_BREAK 0x5f 呼梯继电器断开 \n" +
                "MINOR_CALL_LADDER_RELAY_CLOSE 0x60 呼梯继电器闭合 \n" +
                "MINOR_AUTO_KEY_RELAY_BREAK 0x61 自动按键继电器断开 \n" +
                "MINOR_AUTO_KEY_RELAY_CLOSE 0x62 自动按键继电器闭合 \n" +
                "MINOR_KEY_CONTROL_RELAY_BREAK 0x63 按键梯控继电器断开 \n" +
                "MINOR_KEY_CONTROL_RELAY_CLOSE 0x64 按键梯控继电器闭合 \n" +
                "MINOR_EMPLOYEENO_AND_PW_PASS 0x65 工号加密码认证通过 \n" +
                "MINOR_EMPLOYEENO_AND_PW_FAIL 0x66 工号加密码认证失败 \n" +
                "MINOR_EMPLOYEENO_AND_PW_TIMEOUT 0x67 工号加密码认证超时 \n" +
                "MINOR_HUMAN_DETECT_FAIL 0x68 真人检测失败 \n" +
                "MINOR_PEOPLE_AND_ID_CARD_COMPARE_PASS 0x69 人证比对通过 \n" +
                "MINOR_PEOPLE_AND_ID_CARD_COMPARE_FAIL 0x70 人证比对失败 \n" +
                "MINOR_CERTIFICATE_BLOCKLIST 0x71 黑名单事件 \n" +
                "MINOR_LEGAL_MESSAGE 0x72 合法短信 \n" +
                "MINOR_ILLEGAL_MESSAGE 0x73 非法短信 \n" +
                "MINOR_MAC_DETECT 0x74 MAC侦测 \n" +
                "MINOR_DOOR_OPEN_OR_DORMANT_FAIL 0x75 门状态常闭或休眠状态认证失败 \n" +
                "MINOR_AUTH_PLAN_DORMANT_FAIL 0x76 认证计划休眠模式认证失败 \n" +
                "MINOR_AUTH_PLAN_DORMANT_FAIL 0x76 认证计划休眠模式认证失败 \n" +
                "MINOR_CARD_ENCRYPT_VERIFY_FAIL 0x77 卡加密校验失败 \n" +
                "MINOR_SUBMARINEBACK_REPLY_FAIL 0x78 反潜回服务器应答失败 \n" +
                "MINOR_TRAILING 0x85 尾随通行 \n" +
                "MINOR_REVERSE_ACCESS 0x86 反向闯入 \n" +
                "MINOR_FORCE_ACCESS 0x87 外力冲撞 \n" +
                "MINOR_CLIMBING_OVER_GATE 0x88 翻越 \n" +
                "MINOR_PASSING_TIMEOUT 0x89 通行超时 \n" +
                "MINOR_INTRUSION_ALARM 0x8a 误闯报警 \n" +
                "MINOR_FREE_GATE_PASS_NOT_AUTH 0x8b 闸机自由通行时未认证通过 \n" +
                "MINOR_DROP_ARM_BLOCK 0x8c 摆臂被阻挡 \n" +
                "MINOR_DROP_ARM_BLOCK_RESUME 0x8d 摆臂阻挡消除 \n" +
                "MINOR_LOCAL_FACE_MODELING_FAIL 0x8e 设备升级本地人脸建模失败 \n" +
                "MINOR_STAY_EVENT 0x8f 逗留事件 \n" +
                "MINOR_PASSWORD_MISMATCH 0x97 密码不匹配 \n" +
                "MINOR_EMPLOYEE_NO_NOT_EXIST 0x98 工号不存在 \n" +
                "MINOR_COMBINED_VERIFY_PASS 0x99 组合认证通过 \n" +
                "MINOR_COMBINED_VERIFY_TIMEOUT 0x9a 组合认证超时 \n" +
                "MINOR_VERIFY_MODE_MISMATCH 0x9b 认证方式不匹配 \n";

        String[] split1 = StringUtils.split(str, "\n");
        ArrayList<String> list = new ArrayList<>();
        for (String m : split1) {
            m = m.trim();
//            System.out.println(m);
            String[] s = StringUtils.split(m, " ");
            // MINOR_NET_BROKEN("MINOR_NET_BROKEN", "27", "网络断开", "03"),
            String s1 = s[1];
            String substring = s1.substring(2);
            if (substring.length() < 2){
                substring = "0"+ substring;
            }
            if(!list.contains(substring)){
                String format = String.format("%s(\"%s\",\"%s\",\"%s\",\"%s\"),", s[0], s[0], substring, s[2], "05");
                list.add(substring);
                System.out.println(format);
            }
//            System.out.println("====================================================================");
        }
    }


}
