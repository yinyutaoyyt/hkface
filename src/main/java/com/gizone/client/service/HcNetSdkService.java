package com.gizone.client.service;

import com.gizone.client.pojo.po.*;
import com.gizone.client.utils.JsonResult;

/**
 * @author yyt
 */
public interface HcNetSdkService {

    /**
     * 查询人员
     *  * @param po
     * @return
     */
    JsonResult searchPerson(HkFaceSearchPO po);

    /**
     *添加人员
     * @param po
     * @return
     */
    JsonResult addPerson(HkFaceAddOrUpdatePO po);

    /**
     *批量添加人员
     * @param po
     * @return
     */
    JsonResult batchAddPerson(HkFaceBatchAddPO po);

    /**
     *修改人员信息
     * @param po
     * @return
     */
    JsonResult editPerson(HkFaceAddOrUpdatePO po);

    /**
     *修改人员信息
     * @param hkFaceDelPO
     * @return
     */
    JsonResult delPerson(HkFaceDelPO hkFaceDelPO);

    /**
     * 查询卡号
     * @param po
     * @return
     */
    JsonResult searchCard(HkFaceSearchCardPO po);

    /**
     * 新增卡号
     * @param po
     * @return
     */
    JsonResult addCard(HkFaceAddCardPO po);

    /**
     * 布防
     * @param po
     * @return
     */
    JsonResult startArmed(HkFaceSearchPO po);

    /**
     * 撤销布防
     * @param po
     * @return
     */
    JsonResult closeArmed(HkFaceSearchPO po);

}

