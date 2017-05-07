package com.sashaq.service.bean;

import com.sashaq.entity.User;

import java.util.List;

public interface UserService {
    User create(User user);

    User getById(Integer userId);

    User getByToken(String userToken);

    boolean deleteById(Long userId);

    List<User> getUninvolvedUsers();
}
