package com.sashaq.exception;

import com.sashaq.entity.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final String friendlyMessage;
    private final ErrorCode errorCode;

    public BusinessException(String friendlyMessage, ErrorCode errorCode) {
        this.friendlyMessage = friendlyMessage;
        this.errorCode = errorCode;
    }
}
