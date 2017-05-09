package com.sashaq.dao.impl;

import com.sashaq.dao.ProductDao;
import com.sashaq.dao.mapper.ProductShipTypeRowMapper;
import com.sashaq.entity.Product;
import com.sashaq.entity.ShipType;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sashaq.core.util.constant.StringConstant.*;
import static com.sashaq.dao.impl.ShipTypeDaoImpl.SHIP_TYPE_ROW_MAPPER;
import static java.util.stream.Collectors.toList;

@Repository
public class ProductDaoImpl extends BaseDao<Product> implements ProductDao {

    public ProductDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, PRODUCT);
    }

    @Override
    public Product create(Product product) {
        Number key = getSimpleInsert().executeAndReturnKey(createParameterSource(product));

        final Integer newId = key.intValue();

        List<Object[]> collect = product.getShipTypes()
                                        .stream()
                                        .map(shipType -> new Object[]{newId, shipType.getId()})
                                        .collect(toList());

        getJdbcTemplate().batchUpdate("INSERT INTO product_ship_type(product_id, ship_type_id) VALUES (?,?)",
                                      collect);
        return getById(newId);
    }

    private static MapSqlParameterSource createParameterSource(final Product product) {
        return new MapSqlParameterSource().addValue(NAME, product.getName())
                                          .addValue(DESCRIPTION, product.getDescription())
                                          .addValue(PRICE, product.getPrice())
                                          .addValue(QUANTITY, product.getQuantity());
    }

    @Override
    public Integer getIdByName(String name) {
        return getJdbcTemplate().queryForObject("SELECT id FROM product WHERE name = ?",
                                                params(name),
                                                Integer.class);

    }

    @Override
    public List<Product> getAll() {
        String sql = "SELECT p.id AS p_id, p.name AS p_name, p.description, p.price, p.quantity, t.id, t.name, t.cost FROM product p " +
                     "JOIN product_ship_type st ON p.id = st.product_id " +
                     "JOIN ship_type t ON st.ship_type_id = t.id";

        return getJdbcTemplate().query(sql, ProductShipTypeRowMapper.getInstance());
    }

    @Override
    public Product getById(Integer productId) {
        String sql = "SELECT p.id AS p_id, p.name AS p_name, p.description, p.price, p.quantity, t.id, t.name, t.cost FROM product p " +
                     "JOIN product_ship_type st ON p.id = st.product_id " +
                     "JOIN ship_type t ON st.ship_type_id = t.id " +
                     "WHERE p.id = ?";
        List<Product> result = getJdbcTemplate().query(sql, params(productId), ProductShipTypeRowMapper.getInstance());

        if (CollectionUtils.isEmpty(result)) {
            throw new EmptyResultDataAccessException(1);
        }

        return result.get(0);
    }

    @Override
    public List<ShipType> getShipTypesInProduct(Integer productId) {
        String sql = "SELECT st.id, st.name, st.cost FROM ship_type st " +
                     "JOIN product_ship_type pst ON st.id = pst.ship_type_id AND pst.product_id = ?";

        return getJdbcTemplate().query(sql,
                                       params(productId),
                                       SHIP_TYPE_ROW_MAPPER);
    }

    @Override
    public void addQuantity(Integer productId, Integer additionalQuantity) {
        getJdbcTemplate().update("UPDATE product SET quantity = quantity + ? WHERE id = ?",
                                 additionalQuantity,
                                 productId);

    }

    @Override
    public void addShipTypes(Integer productId, List<Integer> additionalShipTypes) {
        getJdbcTemplate().batchUpdate("INSERT INTO product_ship_type (product_id, ship_type_id) VALUES (?,?)",
                                      batchParams(productId, additionalShipTypes));
    }

    @Override
    public void removeShipTypes(Integer productId, List<Integer> deductionShipTypes) {
        getJdbcTemplate().batchUpdate("DELETE FROM product_ship_type WHERE product_id = ? AND ship_type_id = ?",
                                      batchParams(productId, deductionShipTypes));
    }
}
