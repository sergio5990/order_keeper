package com.sashaq.web.rs.common;

import lombok.Getter;

@Getter
public class ResultResponse {
    private final boolean result;

    public ResultResponse(final boolean result) {
        this.result = result;
    }

    public static ResultResponse create(final boolean result) {
        return new ResultResponse(result);
    }
}
