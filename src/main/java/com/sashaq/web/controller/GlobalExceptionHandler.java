package com.sashaq.web.controller;

import com.sashaq.entity.ErrorCode;
import com.sashaq.exception.BadCredentialException;
import com.sashaq.exception.InvalidParameterException;
import com.sashaq.web.rs.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse notValidRequest(MethodArgumentNotValidException exception) {
        FieldError fieldError = exception.getBindingResult().getFieldError();

        fieldError.getField();
        String message = exception.getMessage();
        return ErrorResponse.create(fieldError.getDefaultMessage(),
                                    ErrorCode.RequestError);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidParameterException.class)
    public ErrorResponse notValidRequest(InvalidParameterException exception) {
        return ErrorResponse.create(exception.getFriendlyMessage(),
                                    ErrorCode.RequestError);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialException.class)
    public ErrorResponse notValidRequest(BadCredentialException exception) {
        return ErrorResponse.create(exception.getFriendlyMessage(),
                                    exception.getErrorCode());
    }
}
