package com.sashaq.web.rs;

import lombok.Getter;

@Getter
public class LoginResponse {
    private final UserResponse user;
    private final String token;

    public LoginResponse(final String token, final UserResponse user) {
        this.token = token;
        this.user = user;
    }
}
