package com.sashaq.entity;


public enum ErrorCode {
    RequestError(4000),
    BadCredentialError(4001);

    private int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
