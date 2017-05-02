package com.sashaq.dao.impl;

import com.sashaq.dao.UserDao;
import com.sashaq.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User create(User user) {
        jdbcTemplate.update("INSERT INTO user(username, name, surname, email, password) VALUES (?,?,?,?,?)",
                            user.getUsername(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword());

        return jdbcTemplate.queryForObject(
                "SELECT id, username, name, surname, email FROM user WHERE username = ?",
                new Object[]{user.getUsername()},
                (rs, rowNum) -> new User(rs.getInt("id"),
                                         rs.getString("username"),
                                         rs.getString("name"),
                                         rs.getString("surname"),
                                         rs.getString("email")
                )
        );
    }

    @Override
    public User getById(Integer userId) {
        return jdbcTemplate.queryForObject(
                "SELECT id, username, name, surname, email FROM user WHERE id = ?", new Object[]{userId},
                (rs, rowNum) -> new User(rs.getInt("id"),
                                         rs.getString("username"),
                                         rs.getString("name"),
                                         rs.getString("surname"),
                                         rs.getString("email")
                )
        );

    }

    @Override
    public User getByToken(String userToken) {
        return jdbcTemplate.queryForObject(
                "SELECT u.id, u.username, u.name, u.surname, u.email" +
                        " FROM user u JOIN user_token ut ON u.id = ut.user_id WHERE ut.token = ?",
                new Object[]{userToken},
                (rs, rowNum) -> new User(rs.getInt("id"),
                                         rs.getString("username"),
                                         rs.getString("name"),
                                         rs.getString("surname"),
                                         rs.getString("email")
                )
        );
    }

    @Override
    public int deleteById(Long userId) {
        return jdbcTemplate.update("DELETE FROM user WHERE id = ?", userId);
    }

    @Override
    public List<User> getUninvolvedUsers() {
        // TODO Implement
        return null;
    }
}
