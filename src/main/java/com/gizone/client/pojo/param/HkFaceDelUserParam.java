package com.gizone.client.pojo.param;

import cn.hutool.core.util.ObjectUtil;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yyt
 */
@Data
public class HkFaceDelUserParam {

    private UserInfoDelCond UserInfoDelCond;

    @Data
    static class UserInfoDelCond {
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
     * @param employeeNos 工号
     * @return
     */
    public static HkFaceDelUserParam reParam(List<String> employeeNos) {
        HkFaceDelUserParam hkFaceSearchUserListParam = new HkFaceDelUserParam();
        HkFaceDelUserParam.UserInfoDelCond userInfoDelCond = new UserInfoDelCond();
        hkFaceSearchUserListParam.setUserInfoDelCond(userInfoDelCond);
        if (ObjectUtil.isNotNull(employeeNos) && employeeNos.size() > 0) {
            List<HkFaceDelUserParam.EmployeeNoList> collect = employeeNos.stream().map(e -> new EmployeeNoList(e)).collect(Collectors.toList());
            hkFaceSearchUserListParam.getUserInfoDelCond().setEmployeeNoList(collect);
        }
        return hkFaceSearchUserListParam;
    }
}
