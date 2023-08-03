package com.mik.gateway.result;

public enum ResultCode {
    /**
     *
     */
    SUCCESS(200),
    /**
     *
     */
    SERVER_ERROR(500);


    private Integer code;

    ResultCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
