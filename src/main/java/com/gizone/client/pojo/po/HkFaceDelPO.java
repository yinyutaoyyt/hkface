package com.gizone.client.pojo.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author yyt
 */
@Data
@ApiModel(value = "人脸po-删除人员信息")
public class HkFaceDelPO extends HkFaceBasePO {

    @ApiModelProperty(name = "employeeNos", value = "多个用英文状态下逗号分隔")
    @NotEmpty(message = "employeeNos不能为空")
    private String employeeNos;

}
