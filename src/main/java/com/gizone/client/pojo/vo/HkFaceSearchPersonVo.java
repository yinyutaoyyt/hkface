package com.gizone.client.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @author yyt
 */
@Data
public class HkFaceSearchPersonVo {

    private UserInfoSearch UserInfoSearch;

    @Data
    public static class UserInfoSearch{
        /**
         * 搜索记录唯一标识，用来确认上层客户端是否为同一个
         */
        private String searchID;
        /**
         * 查询状态字符串描述:OK-查询结束,MORE-还 有数据等待查询,NO MATCH-没有匹配数据
         */
        private String responseStatusStrg;
        /**
         *本次返回的记录条数
         */
        private int numOfMatches;
        /**
         * 符合条件的记录总条数
         */
        private int totalMatches;
        /**
         * 用户信息
         */
        private List<HkFaceSearchUserInfoVo> UserInfo;
    }
}
