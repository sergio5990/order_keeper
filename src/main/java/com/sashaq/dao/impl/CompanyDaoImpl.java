package com.sashaq.dao.impl;

import com.sashaq.dao.CompanyDao;
import com.sashaq.entity.Company;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import static com.sashaq.core.util.constant.StringConstant.*;

@Repository
public class CompanyDaoImpl extends BaseDao<Company> implements CompanyDao {
    private static final RowMapper<Company> COMPANY_ROW_MAPPER = (rs, rowNum) -> new Company(rs.getInt(ID),
                                                                                             rs.getString(NAME),
                                                                                             rs.getString(ADDRESS),
                                                                                             rs.getString(PHONE),
                                                                                             null);

    public CompanyDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, COMPANY);
    }

    @Override
    public int createCompany(Company company) {
        Number key = getSimpleInsert().executeAndReturnKey(createParameterSource(company));
        return key.intValue();
    }

    @Override
    public Company getCompanyByUserId(Integer userId) {
        return getJdbcTemplate().queryForObject(
                "SELECT * FROM company WHERE company.contact_user = ?",
                params(userId),
                COMPANY_ROW_MAPPER
        );
    }

    private static SqlParameterSource createParameterSource(final Company company) {
        return new MapSqlParameterSource().addValue(NAME, company.getName())
                                          .addValue(ADDRESS, company.getAddress())
                                          .addValue(PHONE, company.getPhone())
                                          .addValue(CONTACT_USER, company.getContactUser().getId());
    }
}
