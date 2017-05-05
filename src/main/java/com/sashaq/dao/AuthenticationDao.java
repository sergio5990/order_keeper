package com.sashaq.dao;

import java.time.LocalDateTime;

public interface AuthenticationDao {
    Integer canLogin(String username, String password);

    void saveOrUpdateToken(String token, Integer userId, LocalDateTime expireDate);
}
