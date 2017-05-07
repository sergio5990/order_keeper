package com.sashaq.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.Collection;
import java.util.List;

import static com.sashaq.core.util.constant.StringConstant.ID;
import static java.util.stream.Collectors.toList;

class BaseDao<T> {
    final JdbcTemplate jdbcTemplate;
    final SimpleJdbcInsert simpleInsert;

    BaseDao(final JdbcTemplate jdbcTemplate, final String tableName) {
        this.jdbcTemplate = jdbcTemplate;
        simpleInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName(tableName)
                                                         .usingGeneratedKeyColumns(ID);
    }

    BaseDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleInsert = null; //todo not good
    }

    static Object[] params(Object... params) {
        return params;
    }

    static List<Object[]> batchParams(final Object duplicateValue, final Collection<?> values) {
        return values.stream()
                     .map(o -> new Object[]{duplicateValue, o})
                     .collect(toList());
    }
}
