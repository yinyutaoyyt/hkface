package com.gizone.client.pojo.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author yyt
 */
@Data
@ApiModel(value = "添加人员-批量")
public class HkFaceBatchAddPO extends HkFaceBasePO {

    @ApiModelProperty("人员集合")
    @NotNull(message = "人员集合不能为空")
    List<HkFaceAddOrUpdatePO> persons;
}
