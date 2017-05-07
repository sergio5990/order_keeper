package com.sashaq.web.rs;

import com.sashaq.entity.ErrorCode;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private final int errorCode;
    private final String message;

    private ErrorResponse(final int errorCode, final String message) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public static ErrorResponse create(final ErrorCode errorCode, final String message) {
        return new ErrorResponse(errorCode.getCode(), message);
    }

}
