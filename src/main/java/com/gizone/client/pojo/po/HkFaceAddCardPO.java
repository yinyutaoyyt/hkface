package com.gizone.client.pojo.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author yyt
 */
@Data
@ApiModel(value="人脸po-新增卡")
public class HkFaceAddCardPO extends  HkFaceBasePO{

    @ApiModelProperty(value = "工号")
    @NotEmpty(message = "工号不能为空")
    private String employeeNo;

    @ApiModelProperty("cardNo")
    private String cardNo;

}
