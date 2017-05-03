package com.sashaq.dao.impl;

import com.sashaq.dao.ShipTypeDao;
import com.sashaq.entity.Shiptype;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShipTypeDaoImpl implements ShipTypeDao {
    private final JdbcTemplate jdbcTemplate;

    public ShipTypeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Shiptype create(String name, Float cost) {
        SimpleJdbcInsert productInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("ship_type")
                .usingGeneratedKeyColumns("id");
        Number key = productInsert.executeAndReturnKey(
                new MapSqlParameterSource()
                        .addValue("name", name)
                        .addValue("cost", cost));

        final Integer newId = key.intValue();

        return jdbcTemplate.queryForObject(
                "SELECT * FROM ship_type WHERE id = ?",
                new Object[]{newId},
                (rs, rowNum) -> new Shiptype(rs.getInt("id"),
                                             rs.getString("name"),
                                             rs.getFloat("cost")
                )
        );
    }

    @Override
    public Shiptype update(Integer id, String name, Float cost) {
        jdbcTemplate.update("UPDATE ship_type SET name=?, cost=? WHERE id=?",
                            name, cost);
        return jdbcTemplate.queryForObject(
                "SELECT * FROM ship_type WHERE name = ?",
                new Object[]{name},
                (rs, rowNum) -> new Shiptype(rs.getInt("id"),
                                             rs.getString("name"),
                                             rs.getFloat("cost")
                )
        );
    }

    @Override
    public Shiptype getById(Integer shipTypeId) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM ship_type WHERE id = ?",
                new Object[]{shipTypeId},
                (rs, rowNum) -> new Shiptype(rs.getInt("id"),
                                             rs.getString("name"),
                                             rs.getFloat("cost")
                )
        );
    }

    @Override
    public List<Shiptype> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM ship_type",
                (rs, rowNum) -> new Shiptype(rs.getInt("id"),
                                             rs.getString("name"),
                                             rs.getFloat("cost")
                )
        );
    }
}
