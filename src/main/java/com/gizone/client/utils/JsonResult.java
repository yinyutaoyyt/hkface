package com.gizone.client.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Data
@ApiModel
@NoArgsConstructor
@Accessors(chain = true)
public class JsonResult<T> {
    @ApiModelProperty(value = "错误码，0：成功，其他：失败", example = "0")
    private long code;
    @ApiModelProperty("错误消息")
    private String msg;
    @ApiModelProperty("错误堆栈")
    private String stacks;
    @ApiModelProperty("返回数据，可以是任意类型的值")
    private T data;

    private JsonResult(long c, String m, String s, T d) {
        code = c;
        msg = m;
        stacks = s;
        data = d;
    }

    public static <T> JsonResult<T> success() {
        return new JsonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), null, null);
    }

    public static <T> JsonResult<T> success(T d) {
        return new JsonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), null, d);
    }

    public static <T> JsonResult<T> successMap(String key, T d) {
        Map<String, T> map = new HashMap<>(1);
        map.put(key, d);
        return new JsonResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), null, map);
    }

    public static <T> JsonResult<T> success(String msg, T d) {
        return new JsonResult<>(ResultCode.SUCCESS.getCode(), msg, null, d);
    }

    public static <T> JsonResult<T> failed() {
        return new JsonResult<>(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMsg(), null, null);
    }

    public static <T> JsonResult<T> failed(String msg) {
        return new JsonResult<>(ResultCode.FAILED.getCode(), msg, null, null);
    }

    public static <T> JsonResult<T> failed(String msg, String stacks) {
        return new JsonResult<>(ResultCode.FAILED.getCode(), msg, stacks, null);
    }
}
