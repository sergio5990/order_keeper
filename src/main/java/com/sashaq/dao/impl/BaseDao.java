package com.sashaq.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.Collection;
import java.util.List;

import static com.sashaq.core.util.constant.StringConstant.ID;
import static java.util.stream.Collectors.toList;

class BaseDao<T> {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleInsert;

    BaseDao(final JdbcTemplate jdbcTemplate, final String tableName) {
        this.jdbcTemplate = jdbcTemplate;
        simpleInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName(tableName)
                                                         .usingGeneratedKeyColumns(ID);
    }

    BaseDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleInsert = null;
    }

    static Object[] params(Object... params) {
        return params;
    }

    static List<Object[]> batchParams(final Object duplicateValue, final Collection<?> values) {
        return values.stream()
                     .map(o -> new Object[]{duplicateValue, o})
                     .collect(toList());
    }

    JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    SimpleJdbcInsert getSimpleInsert() {
        if (simpleInsert != null) {
            return simpleInsert;
        } else {
            throw new UnsupportedOperationException("insert is not allowed");
        }
    }
}
