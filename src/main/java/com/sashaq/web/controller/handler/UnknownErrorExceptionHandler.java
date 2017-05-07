package com.sashaq.web.controller.handler;

import com.sashaq.entity.ErrorCode;
import com.sashaq.web.rs.common.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UnknownErrorExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(UnknownErrorExceptionHandler.class);
    private static final String UNKNOWN_ERROR = "Unknown error";

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ErrorResponse notValidRequest(Exception exception) {
        LOG.error("unknown error", exception);

        return ErrorResponse.create(ErrorCode.UNKNOWN_ERROR, UNKNOWN_ERROR);
    }
}
