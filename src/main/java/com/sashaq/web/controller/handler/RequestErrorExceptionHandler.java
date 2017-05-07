package com.sashaq.web.controller.handler;

import com.sashaq.entity.ErrorCode;
import com.sashaq.exception.InvalidParameterException;
import com.sashaq.web.rs.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RequestErrorExceptionHandler {
    private static final String MESSAGE_TEMPLATE = "parameter '%s' with error: %s";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse notValidRequest(MethodArgumentNotValidException exception) {
        FieldError fieldError = exception.getBindingResult().getFieldError();
        String message = fieldError.getDefaultMessage();
        String field = fieldError.getField();

        return ErrorResponse.create(ErrorCode.BAD_REQUEST,
                                    createMessage(field, message));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidParameterException.class)
    public ErrorResponse notValidRequest(InvalidParameterException exception) {

        return ErrorResponse.create(ErrorCode.BAD_REQUEST, exception.getFriendlyMessage());
    }

    private static String createMessage(final String field, final String message) {
        return String.format(MESSAGE_TEMPLATE, field, message);
    }
}
