package com.gizone.client.pojo.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author yyt
 */
@Data
public class HkFaceSearchCardInfoVo {
    /**
     * 工号（人员 ID）
     */
    private String employeeNo;
    /**
     * 卡号
     */
    private String cardNo;
    /**
     * 卡号
     */
    private String leaderCard;
    /**
     * 卡类型
     */
    private String cardType;
}
