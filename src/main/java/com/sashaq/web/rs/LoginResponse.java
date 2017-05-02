package com.sashaq.web.rs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private UserResponse user;
    private String token;

    public LoginResponse(String token, UserResponse user){
        this.token = token;
        this.user = user;
    }
}
