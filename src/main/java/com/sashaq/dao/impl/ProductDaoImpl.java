package com.sashaq.dao.impl;

import com.sashaq.dao.ProductDao;
import com.sashaq.entity.Product;
import com.sashaq.entity.Shiptype;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductDaoImpl implements ProductDao {
    private final JdbcTemplate jdbcTemplate;

    public ProductDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product create(Product product) {
        SimpleJdbcInsert productInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("product")
                .usingGeneratedKeyColumns("id");
        Number key = productInsert.executeAndReturnKey(
                new MapSqlParameterSource()
                        .addValue("name", product.getName())
                        .addValue("description", product.getDescription())
                        .addValue("price", product.getPrice())
                        .addValue("quantity", product.getQuantity()));

        final Integer newId = key.intValue();

        List<Object[]> collect = product.getShipTypes()
                                        .stream()
                                        .map(shipType -> new Object[]{newId, shipType.getId()})
                                        .collect(Collectors.toList());

        jdbcTemplate.batchUpdate("INSERT INTO product_ship_type(product_id, ship_type_id) VALUES (?,?)",
                                 collect);
        return getById(newId);
    }

    @Override
    public Integer getIdByName(String name) {
        return jdbcTemplate.queryForObject(
                "SELECT id FROM product WHERE name = ?",
                new Object[]{name},
                (rs, rowNum) -> rs.getInt("id")
        );
    }

    @Override
    public List<Product> getAll() {
        return jdbcTemplate.query(
                "SELECT id, name, description, price, quantity FROM product",
                (rs, rowNum) -> new Product(rs.getInt("id"),
                                            rs.getString("name"),
                                            rs.getString("description"),
                                            rs.getFloat("price"),
                                            rs.getInt("quantity"),
                                            getShipTypesInProduct(rs.getInt("id"))
                )
        );
    }

    @Override
    public Product getById(Integer productId) {
        return jdbcTemplate.queryForObject(
                "SELECT id, name, description, price, quantity FROM product WHERE id = ?",
                new Object[]{productId},
                (rs, rowNum) ->
                        new Product(rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getString("description"),
                                    rs.getFloat("price"),
                                    rs.getInt("quantity"),
                                    getShipTypesInProduct(rs.getInt("id"))
                        )
        );
    }

    @Override
    public List<Shiptype> getShipTypesInProduct(Integer productId) {
        return jdbcTemplate.query(
                "SELECT ship_type_id FROM product_ship_type WHERE product_id = ?",
                new Object[]{productId},
                (rs, rowNum) -> new Shiptype(rs.getInt("ship_type_id"),
                                             rs.getString("ship_type_id"),
                                             rs.getFloat("ship_type_id"))
        );
    }

    @Override
    public Product addQuantity(Integer productId, Integer additionalQuantity) {
        jdbcTemplate.update("UPDATE product SET quantity = quantity + ? WHERE id = ?",
                            additionalQuantity, productId);
        return getById(productId);
    }

    @Override
    public Product addShipTypes(Integer productId, List<Integer> additionalShipTypes) {
        additionalShipTypes.forEach(item ->
                                            jdbcTemplate.update(
                                                    "INSERT INTO product_ship_type(product_id, ship_type_id) VALUES (?,?)",
                                                    productId,
                                                    item)
        );
        return getById(productId);
    }

    @Override
    public Product removeShipTypes(Integer productId, List<Integer> deductionShipTypes) {
        deductionShipTypes.forEach(item ->
                                           jdbcTemplate.update(
                                                   "DELETE FROM product_ship_type WHERE product_id = ? AND ship_type_id = ?",
                                                   productId,
                                                   item)
        );
        return getById(productId);
    }
}
