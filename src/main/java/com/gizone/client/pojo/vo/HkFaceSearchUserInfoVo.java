package com.gizone.client.pojo.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author yyt
 */
@Data
public class HkFaceSearchUserInfoVo {
    /**
     * 工号（人员 ID）
     */
    private String employeeNo;
    /**
     * 姓名
     */
    private String name;
    /**
     * 人员类型，normal-普通人（主人）， visitor-来宾（访客），blackList-禁止名单人
     */
    private String userType;
    /**
     * 是否关门延迟，true-是，
     * false-否
     */
    private String closeDelayEnabled;

    private Valid Valid;
    /**
     * 所属群组
     */
    private String belongGroup;

    private String password;
    /**
     *  "1,3",	//可选，string，门权限（代表对门 1、门 3 有权限）
     * （锁权限，此处为锁 ID，可填写多个，代表对锁 1、锁 3 有权限）
     */
    private String doorRight;
    /**
     * 门权限计划（锁权限计划）
     */
    private List<RightPlan> RightPlan;
    /**
     * 最大认证次数，0 为无次数
     */
    private int maxOpenDoorTime;
    /**
     * 已认证次数
     */
    private int openDoorTime;
    /**
     * 房间号
     */
    private int roomNumber;
    /**
     * 层号
     */
    private int floorNumber;
    /**
     * 否具有设备本地 UI 访问权限，
     * true-有权限，false-无权限
     */
    private Boolean localUIRight;
    /**
     *人脸图片对应的人员性别:  male-男,  female-女,  unknown-未知
     */
    private String gender;
    /**
     *，关联卡数量
     */
    private int numOfCard;
    /**
     * 关联人脸数量
     */
    private int numOfFace;

    private String PersonInfoExtends;

    @Data
    class Valid{
        /**
         * 使能有效期，false-不使能，true 使能
         */
        private String enable;
        /**
         * 有效期起始时间
         */
        private Date beginTime;
        /**
         * 有效期结束时间
         */
        private Date endTime;
        /**
         * local-设备本地时间，
         * UTC-UTC 时间
         */
        private String timeType;
    }
    @Data
    class RightPlan{
        /**
         * integer，门编号（锁 ID）
         */
        private int doorNo;
        /**
         * 计划模板编号，同
         * 个门不同计划模板采用权限或的方式处理
         */
        private String planTemplateNo;
    }
}
