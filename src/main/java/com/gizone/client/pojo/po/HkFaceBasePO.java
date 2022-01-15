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
@ApiModel(value="")
public class HkFaceBasePO {

    @ApiModelProperty("ip")
    @NotNull
    private String ip;

    @ApiModelProperty("端口")
    @NotNull
    private short port;

    @ApiModelProperty("userName")
    @NotEmpty
    private String userName;

    @ApiModelProperty("passWord")
    @NotEmpty
    private String passWord;

}
