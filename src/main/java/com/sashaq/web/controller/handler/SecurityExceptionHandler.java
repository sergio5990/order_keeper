package com.sashaq.web.controller.handler;

import com.sashaq.exception.BadCredentialException;
import com.sashaq.web.rs.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SecurityExceptionHandler {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialException.class)
    public ErrorResponse notValidRequest(BadCredentialException exception) {

        return ErrorResponse.create(exception.getErrorCode(),
                                    exception.getFriendlyMessage());
    }
}
