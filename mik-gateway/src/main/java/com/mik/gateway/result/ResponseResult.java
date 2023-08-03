package com.mik.gateway.result;

import lombok.Data;

@Data
public class ResponseResult<T> {
    private Integer code;
    private String msg;
    private T data;

    public ResponseResult() {
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public static ResponseResult success(String msg){
        return new ResponseResult(ResultCode.SUCCESS.getCode(), msg);
    }
}
