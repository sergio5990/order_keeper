package com.sashaq.exception;

import com.sashaq.entity.ErrorCode;

public class BadCredentialException extends BusinessException {
    public BadCredentialException() {
        super("Wrong Credential", ErrorCode.BadCredentialError);
    }
}
