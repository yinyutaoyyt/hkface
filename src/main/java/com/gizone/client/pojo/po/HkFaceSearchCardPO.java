package com.gizone.client.pojo.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author yyt
 */
@Data
@ApiModel(value = "人脸po-查询人员下卡信息")
public class HkFaceSearchCardPO extends HkFaceBasePO {

    @ApiModelProperty(value = "工号")
    @NotEmpty(message = "工号不能为空")
    private String employeeNo;
}
