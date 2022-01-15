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
@ApiModel(value="添加人员-单个")
public class HkFaceAddOrUpdatePO extends  HkFaceBasePO{

    @ApiModelProperty("添加人员姓名")
    @NotEmpty(message = "人员姓名不能为空")
    private String name;

    @ApiModelProperty("添加人员密码")
    @NotNull(message = "人员密码不能为null")
    private String userPwd;

    @ApiModelProperty(value = "工号")
    @NotEmpty(message = "工号不能为空")
    private String employeeNo;

    @ApiModelProperty("cardNo")
    private String cardNo;

    @ApiModelProperty("faceUrl")
    private String faceUrl;

    @ApiModelProperty("有效期起始时间")
    @NotNull(message = "有效期起始时间不能为null")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date beginTime;

    @ApiModelProperty("有效期结束时间")
    @NotNull(message = "有效期结束时间不能为null")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

}
