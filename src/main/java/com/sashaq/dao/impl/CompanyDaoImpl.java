package com.sashaq.dao.impl;

import com.sashaq.dao.CompanyDao;
import com.sashaq.entity.Company;
import com.sashaq.service.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyDaoImpl implements CompanyDao {
    private final JdbcTemplate jdbcTemplate;
    private final UserService userService;


    public CompanyDaoImpl(JdbcTemplate jdbcTemplate, UserService userService) {
        this.jdbcTemplate = jdbcTemplate;
        this.userService = userService;
    }

    @Override
    public Company createCompany(Company company) {
        SimpleJdbcInsert productInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("company")
                .usingGeneratedKeyColumns("id");
        Number key = productInsert.executeAndReturnKey(
                new MapSqlParameterSource()
                        .addValue("name", company.getName())
                        .addValue("address", company.getAddress())
                        .addValue("phone", company.getPhone())
                        .addValue("contact_user", company.getContactUser().getId()));

        final Integer newId = key.intValue();

        return jdbcTemplate.queryForObject("SELECT id, name, address, phone, contact_user FROM company WHERE id = ?",
                           new Object[]{newId},
                           (rs, rowNum) ->
                                   new Company(rs.getInt("id"),
                                               rs.getString("name"),
                                               rs.getString("address"),
                                               rs.getString("phone"),
                                               userService.getById(rs.getInt("contact_user"))
                                   ));
    }
}
