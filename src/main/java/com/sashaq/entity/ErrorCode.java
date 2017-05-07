package com.sashaq.entity;


public enum ErrorCode {
    BAD_REQUEST(4000),
    BAD_CREDENTIAL(4001),

    UNKNOWN_ERROR(5000);

    private int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
