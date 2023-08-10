package com.mik.core.pojo;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code = 0;
    private String msg = "";
    private T data;

    public Result() {
    }

    public Result(Integer code) {
        this.code = code;
    }

    public Result(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> Result<T> success(T data){
        return new Result<>(200, data);
    }

    public static Result error(String msg){
        return new Result<>(500, msg);
    }

    public static  Result success(){
        return new Result<>(200);
    }

}
