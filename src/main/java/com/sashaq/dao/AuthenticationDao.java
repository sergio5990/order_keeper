package com.sashaq.dao;

import com.sashaq.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface AuthenticationDao {
    public Integer canLogin(String username, String password);

    public String createToken(String token, Integer userId, LocalDateTime expireDate);
}
