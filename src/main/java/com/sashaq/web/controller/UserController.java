package com.sashaq.web.controller;

import com.sashaq.entity.User;
import com.sashaq.exception.InvalidParameterException;
import com.sashaq.service.bean.UserService;
import com.sashaq.service.builder.UserBuilder;
import com.sashaq.web.rq.UserRequest;
import com.sashaq.web.rs.UserResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public UserResponse createUser(@Validated @RequestBody UserRequest request) {
        User user = new UserBuilder().username(request.getUsername())
                                     .name(request.getName())
                                     .surname(request.getSurname())
                                     .email(request.getEmail())
                                     .password(request.getPassword())
                                     .build();
        return new UserResponse(userService.create(user));
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public UserResponse getUser(@PathVariable Integer userId) {
        if (userId < 0) {
            throw new InvalidParameterException("userId", userId);
        }
        return new UserResponse(userService.getById(userId));
    }

    @RequestMapping(value = "/getUninvolved", method = RequestMethod.GET)
    public List<User> getUninvolvedUsers() {
        return userService.getUninvolvedUsers();
    }


    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE)
    public boolean deleteUser(@PathVariable Long userId) {
        return userService.deleteById(userId);
    }
}
