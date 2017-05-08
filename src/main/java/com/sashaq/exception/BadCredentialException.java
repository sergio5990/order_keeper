package com.sashaq.exception;

import com.sashaq.entity.ErrorCode;

public class BadCredentialException extends BusinessException {
    private static final String WRONG_CREDENTIAL = "Wrong Credential";

    public BadCredentialException() {
        super(WRONG_CREDENTIAL, ErrorCode.BAD_CREDENTIAL);
    }
}
