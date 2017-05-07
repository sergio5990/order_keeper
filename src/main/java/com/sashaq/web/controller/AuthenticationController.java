package com.sashaq.web.controller;

import com.sashaq.entity.User;
import com.sashaq.service.bean.AuthenticationService;
import com.sashaq.service.bean.UserService;
import com.sashaq.web.rq.LoginRequest;
import com.sashaq.web.rs.LoginResponse;
import com.sashaq.web.rs.UserResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    public AuthenticationController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse login(@Validated @RequestBody LoginRequest request) {
        String token = authenticationService.login(request.getUsername(), request.getPassword());

        User user = userService.getByToken(token);
        UserResponse userResponse = new UserResponse(user);

        return new LoginResponse(token, userResponse);
    }
}
