package com.sashaq.web.controller;

import com.sashaq.entity.User;
import com.sashaq.service.bean.UserService;
import com.sashaq.service.builder.UserBuilder;
import com.sashaq.web.rq.UserRequest;
import com.sashaq.web.rs.UserResponse;
import com.sashaq.web.rs.common.ResultResponse;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sashaq.core.util.constant.StringConstant.USER_ID;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public UserResponse createUser(@Validated @RequestBody UserRequest request) {
        User user = UserBuilder.buildFromRequest(request);

        return new UserResponse(userService.save(user));
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@Validated @Range @PathVariable(USER_ID) Integer userId) {

        return new UserResponse(userService.getById(userId));
    }

    @GetMapping("/getUninvolved")
    public List<User> getUninvolvedUsers() {

        return userService.getUninvolvedUsers();
    }


    @DeleteMapping("/delete/{userId}")
    public ResultResponse deleteUser(@PathVariable(USER_ID) Long userId) {
        boolean isDeleted = userService.deleteById(userId);

        return ResultResponse.create(isDeleted);
    }
}
