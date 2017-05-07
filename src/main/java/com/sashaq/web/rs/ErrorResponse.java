package com.sashaq.web.rs;

import com.sashaq.entity.ErrorCode;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private final String message;
    private final int errorCode;

    private ErrorResponse(final String message, final int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public static ErrorResponse create(String message, ErrorCode errorCode){
        return new ErrorResponse(message, errorCode.getCode());
    }

}
