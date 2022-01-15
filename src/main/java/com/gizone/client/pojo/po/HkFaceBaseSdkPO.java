package com.gizone.client.pojo.po;

import com.gizone.client.constant.HkfaceEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yyt
 */
@Data
@ApiModel(value="sdk param")
public class HkFaceBaseSdkPO{

    @ApiModelProperty("lUserID")
    private Integer lUserID;

    @ApiModelProperty("iCharEncodeType")
    private Integer iCharEncodeType;

    @ApiModelProperty("hkfaceEnum")
    private HkfaceEnum hkfaceEnum;

}
