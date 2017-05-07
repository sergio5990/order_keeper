package com.sashaq.exception;

import com.sashaq.entity.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidParameterException  extends BusinessException{
    private String parameter;
    private int value;

    public InvalidParameterException(String parameter, Integer value) {
        super("invalid parameter: " + parameter, ErrorCode.BAD_REQUEST);
        this.parameter = parameter;
        this.value = value;
    }
}
