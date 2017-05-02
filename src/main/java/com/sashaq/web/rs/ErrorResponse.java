package com.sashaq.web.rs;

import com.sashaq.entity.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

    private String message;
    private Integer errorCode;

    public static ErrorResponse withRequestError(String message, ErrorCode errorCode){
        ErrorResponse errorResponse =  new ErrorResponse();
        errorResponse.setErrorCode(errorCode.getCode());
        errorResponse.setMessage(message);

        return errorResponse;
    };

}
