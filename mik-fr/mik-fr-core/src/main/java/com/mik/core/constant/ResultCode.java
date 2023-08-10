package com.mik.core.constant;

public enum ResultCode {
    /**
     *
     */
    SUCCESS(200),
    /**
     *
     */
    SERVER_ERROR(500),
    /**
     * Unauthorized
     */
    UNAUTHORTHORIZED(401);


    private Integer code;

    ResultCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
