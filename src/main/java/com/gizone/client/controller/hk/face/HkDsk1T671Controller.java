package com.gizone.client.controller.hk.face;

import com.gizone.client.pojo.po.*;
import com.gizone.client.service.HcNetSdkService;
import com.gizone.client.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author yyt
 */
@RequestMapping("hk/face/dsk1t671")
@RestController
@Api(tags = "【人脸识别终端-DS-K1T671】")
@Slf4j
@Valid
public class HkDsk1T671Controller {

    @Autowired
    HcNetSdkService hcNetSdkService;

    @PostMapping("/searchPerson")
    @ApiOperation(value = "查询人员信息")
    public JsonResult searchPerson(@RequestBody @Valid HkFaceSearchPO po) {
        return hcNetSdkService.searchPerson(po);
    }

    @PostMapping("/addPerson")
    @ApiOperation(value = "添加人员-同时下发卡号和人脸信息")
    public JsonResult addPerson(@RequestBody @Validated HkFaceAddOrUpdatePO po) {
        return hcNetSdkService.addPerson(po);
    }

    @PostMapping("/batchAddPerson")
    @ApiOperation(value = "批量添加人员-同时下发卡号和人脸信息",hidden = true)
    public JsonResult batchAddPerson(@RequestBody @Validated HkFaceBatchAddPO po) {
        return hcNetSdkService.batchAddPerson(po);
    }

    @PostMapping("/editPerson")
    @ApiOperation(value = "修改人员信息")
    public JsonResult editPerson(@RequestBody @Validated HkFaceAddOrUpdatePO po) {
        return hcNetSdkService.editPerson(po);
    }

    @PostMapping("/delPerson")
    @ApiOperation(value = "删除人员信息")
    public JsonResult delPerson(@RequestBody @Validated HkFaceDelPO hkFaceDelPO) {
        return hcNetSdkService.delPerson(hkFaceDelPO);
    }

    @PostMapping("/addCard")
    @ApiOperation(value = "添加卡号")
    public JsonResult addCard(@RequestBody @Validated HkFaceAddCardPO po) {
        return hcNetSdkService.addCard(po);
    }

    @PostMapping("/searchCard")
    @ApiOperation(value = "查询人员下的所有卡号")
    public JsonResult searchCard(@RequestBody @Validated HkFaceSearchCardPO po) {
        return hcNetSdkService.searchCard(po);
    }

    @PostMapping("/startArmed")
    @ApiOperation(value = "开始布防")
    public JsonResult startArmed(@RequestBody @Validated HkFaceSearchPO po) {
        return hcNetSdkService.startArmed(po);
    }

    @PostMapping("/closeArmed")
    @ApiOperation(value = "撤销布防")
    public JsonResult closeArmed(@RequestBody @Validated HkFaceSearchPO po) {
        return hcNetSdkService.closeArmed(po);
    }

}