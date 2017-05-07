package com.sashaq.dao.impl;

import com.sashaq.dao.AuthenticationDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class AuthenticationDaoImpl implements AuthenticationDao {
    private final JdbcTemplate jdbcTemplate;

    public AuthenticationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer canLogin(String username, String password) {
        return jdbcTemplate.queryForObject(
                "SELECT id FROM user WHERE username = ? AND password = ?",
                new Object[]{username, password},
                (rs, rowNum) -> rs.getInt("id")
        );
    }

    @Override
    public void saveOrUpdateToken(String token, Integer userId, LocalDateTime expireDate) {
        jdbcTemplate.update(
                "INSERT INTO user_token(token, user_id, expiration_date) VALUES (?,?,?) ON DUPLICATE KEY UPDATE token = ?, expiration_date = ?",
                token,
                userId,
                expireDate,
                token,
                expireDate);
    }
}
