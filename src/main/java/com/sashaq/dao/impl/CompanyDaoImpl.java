package com.sashaq.dao.impl;

import com.sashaq.dao.CompanyDao;
import com.sashaq.entity.Company;
import com.sashaq.service.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
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
        jdbcTemplate.update("INSERT INTO company(name, address, phone, contact_user) VALUES (?,?,?,?)",
                            company.getName(),
                            company.getAddress(),
                            company.getPhone(),
                            company.getContactUser().getId());

        return jdbcTemplate.queryForObject("SELECT id, name, address, phone, contact_user FROM company WHERE name = ?",
                           new Object[]{company.getName()},
                           (rs, rowNum) ->
                                   new Company(rs.getInt("id"),
                                               rs.getString("name"),
                                               rs.getString("address"),
                                               rs.getString("phone"),
                                               userService.getById(rs.getInt("contact_user"))
                                   ));
    }
}
