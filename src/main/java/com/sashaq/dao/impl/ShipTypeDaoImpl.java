package com.sashaq.dao.impl;

import com.sashaq.dao.ShipTypeDao;
import com.sashaq.entity.ShipType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sashaq.core.util.constant.StringConstant.*;

@Repository
public class ShipTypeDaoImpl extends BaseDao<ShipType> implements ShipTypeDao {
    private static final RowMapper<ShipType> SHIP_TYPE_ROW_MAPPER = (rs, rowNum) -> new ShipType(rs.getInt(ID),
                                                                                                 rs.getString(NAME),
                                                                                                 rs.getFloat(COST));

    ShipTypeDaoImpl(final JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, SHIP_TYPE);
    }

    @Override
    public int save(String name, Float cost) {
        Number key = simpleInsert.executeAndReturnKey(new MapSqlParameterSource().addValue(NAME, name)
                                                                                 .addValue(COST, cost));

        return key.intValue();
    }

    @Override
    public int update(Integer id, String name, Float cost) {
        return jdbcTemplate.update("UPDATE ship_type SET name = ?, cost = ? WHERE id = ?",
                                   name, cost, id);
    }

    @Override
    public ShipType getById(Integer shipTypeId) {
        return jdbcTemplate.queryForObject("SELECT * FROM ship_type WHERE id = ?",
                                           params(shipTypeId),
                                           SHIP_TYPE_ROW_MAPPER);
    }

    @Override
    public List<ShipType> getAll() {
        return jdbcTemplate.query("SELECT * FROM ship_type", SHIP_TYPE_ROW_MAPPER);
    }
}