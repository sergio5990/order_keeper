package com.sashaq.exception;

import com.sashaq.entity.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidParameterException extends BusinessException {

    public InvalidParameterException(String parameter) {
        super("invalid parameter: " + parameter, ErrorCode.BAD_REQUEST);
    }
}
