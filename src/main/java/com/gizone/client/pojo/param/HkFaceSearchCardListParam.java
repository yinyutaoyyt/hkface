package com.gizone.client.pojo.param;

import cn.hutool.core.util.ObjectUtil;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yyt
 */
@Data
public class HkFaceSearchCardListParam {

    private CardInfoSearchCond CardInfoSearchCond;

    @Data
    static class CardInfoSearchCond {
        private String searchID;
        private int searchResultPosition;
        private int maxResults;
        private List<EmployeeNoList> EmployeeNoList;
    }

    @Data
    static class EmployeeNoList {
        private String employeeNo;

        public EmployeeNoList(String employeeNo) {
            this.employeeNo = employeeNo;
        }
    }

    /**
     *
     * @param searchID 搜索唯一标识
     * @param searchResultPosition 开始位置
     * @param maxResults 返回最大条数
     * @param employeeNos 工号
     * @return
     */
    public static HkFaceSearchCardListParam reParam(String searchID, int searchResultPosition, int maxResults, List<String> employeeNos) {
        HkFaceSearchCardListParam hkFaceSearchUserListParam = new HkFaceSearchCardListParam();
        HkFaceSearchCardListParam.CardInfoSearchCond userInfoSearchCond = new CardInfoSearchCond();
        userInfoSearchCond.setSearchID(searchID);
        userInfoSearchCond.setSearchResultPosition(searchResultPosition);
        userInfoSearchCond.setMaxResults(maxResults);
        hkFaceSearchUserListParam.setCardInfoSearchCond(userInfoSearchCond);
        if (ObjectUtil.isNotNull(employeeNos) && employeeNos.size() > 0) {
            List<HkFaceSearchCardListParam.EmployeeNoList> collect = employeeNos.stream().map(e -> new EmployeeNoList(e)).collect(Collectors.toList());
            hkFaceSearchUserListParam.getCardInfoSearchCond().setEmployeeNoList(collect);
        }
        return hkFaceSearchUserListParam;
    }
}
