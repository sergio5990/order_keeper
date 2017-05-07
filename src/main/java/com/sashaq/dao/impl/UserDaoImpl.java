package com.sashaq.dao.impl;

import com.sashaq.dao.UserDao;
import com.sashaq.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

import static com.sashaq.core.util.constant.StringConstant.*;

@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static final RowMapper<User> USER_ROW_MAPPER = (rs, rowNum) -> new User(rs.getInt(ID),
                                                                                    rs.getString(USERNAME),
                                                                                    rs.getString(NAME),
                                                                                    rs.getString(SURNAME),
                                                                                    rs.getString(EMAIL));

    public UserDaoImpl(final JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, USER);
    }

    @Override
    public int save(User user) {
        Number key = simpleInsert.executeAndReturnKey(createParameterSource(user));

        return key.intValue();
    }

    private static SqlParameterSource createParameterSource(final User user) {
        return new MapSqlParameterSource().addValue(USERNAME, user.getUsername())
                                          .addValue(NAME, user.getName())
                                          .addValue(SURNAME, user.getSurname())
                                          .addValue(EMAIL, user.getEmail())
                                          .addValue(PASSWORD, user.getPassword());
    }

    @Override
    public User getById(Integer userId) {
        return jdbcTemplate.queryForObject("SELECT id, username, name, surname, email FROM user WHERE id = ?",
                                           params(userId),
                                           USER_ROW_MAPPER);
    }

    @Override
    public User getByToken(String userToken) {
        String sql = "SELECT u.id, u.username, u.name, u.surname, u.email FROM user u JOIN user_token ut ON u.id = ut.user_id WHERE ut.token = ?";
        return jdbcTemplate.queryForObject(sql,
                                           params(userToken),
                                           USER_ROW_MAPPER);
    }

    @Override
    public int deleteById(Long userId) {
        return jdbcTemplate.update("DELETE FROM user WHERE id = ?", userId);
    }

    @Override
    public List<User> getUninvolvedUsers() {
        // TODO Implement
        return Collections.emptyList();
    }
}
