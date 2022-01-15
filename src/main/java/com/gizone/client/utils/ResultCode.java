package com.gizone.client.utils;


public enum ResultCode {
    /**
     * 服务器处理成功状态码范围 大于等于0
     * 服务器处理抛异常状态码范围 小于0
     */
    FAILED(-1, "失败"),
    SUCCESS(0, "成功"),
    ;
    private long code;
    private String msg;

    private ResultCode(long c, String m) {
        code = c;
        msg = m;
    }

    public long getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
