package com.gizone.client.constant;

import lombok.Getter;

/**
 * @author yyt
 */
@Getter
public enum HkfaceEnum {

    /**
     * 海康人脸枚举定义
     */
    SEARCH_PERSION_URL("POST /ISAPI/AccessControl/UserInfo/Search?format=json", "查询用户信息", 2550, 10 * 1024, false),
    ADD_PERSION_URL("POST /ISAPI/AccessControl/UserInfo/Record?format=json", "添加用户信息", 2550, 1024, true),
    ADD_OR_UPDATE_PERSION_URL("PUT /ISAPI/AccessControl/UserInfo/SetUp?format=json", "添加/修改用户信息", 2550, 1024, true),
    //    UPDATE_PERSION_URL("PUT /ISAPI/AccessControl/UserInfo/Modify?format=json", "修改用户信息", 2550, 1024, true),
    DEL_PERSION_URL("PUT /ISAPI/AccessControl/UserInfo/Delete?format=json", "删除用户信息", 2550, 1024, true),
    SEARCH_CARD_URL("POST /ISAPI/AccessControl/CardInfo/Search?format=json", "查询卡号", 2550, 1024, false),
    ADD_CARD_URL("PUT /ISAPI/AccessControl/CardInfo/SetUp?format=json", "添加卡号", 2550, 1024, true),
    ADD_FACE_PIC_URL("POST /ISAPI/Intelligent/FDLib/FaceDataRecord?format=json ", "下发人脸信息", 2551, 1024, true),
    ;

    /**
     * 接口请求url
     */
    private String method;

    /**
     * 接口名称
     */
    private String name;

    /**
     * 命令
     */
    private int command;

    /**
     * 长度
     */
    private int size;

    /**
     * 返回成功需要再次校验
     */
    private Boolean isFlag;


    HkfaceEnum(String method, String name, int command, int size, Boolean isFlag) {
        this.method = method;
        this.name = name;
        this.command = command;
        this.size = size;
        this.isFlag = isFlag;
    }
}
