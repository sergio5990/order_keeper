package com.sashaq.web.controller;

import com.sashaq.entity.User;
import com.sashaq.service.bean.AuthenticationService;
import com.sashaq.service.bean.UserService;
import com.sashaq.web.rq.LoginRequest;
import com.sashaq.web.rs.LoginResponse;
import com.sashaq.web.rs.UserResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    public AuthenticationController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponse login(@Validated @RequestBody LoginRequest request) {
        String token = authenticationService.login(request.getUsername(), request.getPassword());
        User user = userService.getByToken(token);

        return new LoginResponse(token, new UserResponse(user));
    }
}
