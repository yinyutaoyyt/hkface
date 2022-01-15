package com.gizone.client.pojo.param;

import cn.hutool.core.util.ObjectUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yyt
 */
@Data
public class HkFaceSearchUserListParam {

    private UserInfoSearchCond UserInfoSearchCond;

    @Data
    static class UserInfoSearchCond {
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
    public static HkFaceSearchUserListParam reParam(String searchID, int searchResultPosition, int maxResults, List<String> employeeNos) {
        HkFaceSearchUserListParam hkFaceSearchUserListParam = new HkFaceSearchUserListParam();
        HkFaceSearchUserListParam.UserInfoSearchCond userInfoSearchCond = new UserInfoSearchCond();
        userInfoSearchCond.setSearchID(searchID);
        userInfoSearchCond.setSearchResultPosition(searchResultPosition);
        userInfoSearchCond.setMaxResults(maxResults);
        hkFaceSearchUserListParam.setUserInfoSearchCond(userInfoSearchCond);
        if (ObjectUtil.isNotNull(employeeNos) && employeeNos.size() > 0) {
            List<HkFaceSearchUserListParam.EmployeeNoList> collect = employeeNos.stream().map(e -> new EmployeeNoList(e)).collect(Collectors.toList());
            hkFaceSearchUserListParam.getUserInfoSearchCond().setEmployeeNoList(collect);
        }
        return hkFaceSearchUserListParam;
    }
}
