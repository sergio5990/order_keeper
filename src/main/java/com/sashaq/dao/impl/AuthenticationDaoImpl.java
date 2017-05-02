package com.sashaq.dao.impl;

import com.sashaq.dao.AuthenticationDao;
import com.sashaq.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
    public String createToken(String token, Integer userId, LocalDateTime expireDate) {
        jdbcTemplate.update(
                "INSERT INTO user_token(token, user_id, expiration_date) VALUES (?,?,?) ON DUPLICATE KEY UPDATE token = ?, expiration_date = ?",
                token,
                userId,
                expireDate,
                token,
                expireDate);
        return jdbcTemplate.queryForObject(
                "SELECT token FROM user_token WHERE user_id = ?",
                new Object[]{userId},
                (rs, rowNum) -> rs.getString("token")
        );
    }
}
