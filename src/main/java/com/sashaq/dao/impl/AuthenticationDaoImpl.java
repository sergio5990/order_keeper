package com.sashaq.dao.impl;

import com.sashaq.dao.AuthenticationDao;
import com.sashaq.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class AuthenticationDaoImpl extends BaseDao<User> implements AuthenticationDao {
    public AuthenticationDaoImpl(final JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Integer canLogin(String username, String password) {
        return getJdbcTemplate().queryForObject("SELECT id FROM user WHERE username = ? AND password = ?",
                                                params(username, password),
                                                Integer.class);
    }

    @Override
    public void saveOrUpdateToken(String token, Integer userId, LocalDateTime expireDate) {
        String sql = "INSERT INTO user_token(token, user_id, expiration_date) VALUES (?,?,?) ON DUPLICATE KEY UPDATE token = ?, expiration_date = ?";
        getJdbcTemplate().update(sql,
                                 token,
                                 userId,
                                 expireDate,
                                 token,
                                 expireDate);
    }
}
