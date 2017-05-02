package com.sashaq.service;

import com.sashaq.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User create(User user);

    User getById(Integer userId);

    User getByToken(String userToken);

    boolean deleteById(Long userId);

    List<User> getUninvolvedUsers();
}
