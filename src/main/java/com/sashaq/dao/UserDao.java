package com.sashaq.dao;

import com.sashaq.entity.User;

import java.util.List;

public interface UserDao {
    User create(User user);

    User getById(Integer userId);

    User getByToken(String userToken);

    int deleteById(Long userId);

    List<User> getUninvolvedUsers();
}
