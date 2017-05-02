package com.sashaq.dao.impl;

import com.sashaq.dao.ProductDao;
import com.sashaq.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Array;
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
        jdbcTemplate.update("INSERT INTO product(name, description, price, quantity) VALUES (?,?,?,?)",
                            product.getName(), product.getDescription(), product.getPrice(), product.getQuantity());
        Integer newId = getIdByName(product.getName());
        product.getShipTypes().forEach(item -> {
            jdbcTemplate.update("INSERT INTO product_ship_type(product_id, ship_type_id) VALUES (?,?)",
                                newId, item);
        });
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
                (rs, rowNum) -> {
                    return new Product(rs.getInt(1),
                                       rs.getString(2),
                                       rs.getString(3),
                                       rs.getFloat(4),
                                       rs.getInt(5),
                                       null
                    );
                }
        );
    }

    @Override
    public Product getById(Integer productId) {
        return jdbcTemplate.queryForObject(
                "SELECT pr.id, pr.name, pr.description, pr.price, pr.quantity FROM product pr WHERE pr.id = ?",
                new Object[]{productId},
                (rs, rowNum) -> {
                    return new Product(rs.getInt(1),
                                       rs.getString(2),
                                       rs.getString(3),
                                       rs.getFloat(4),
                                       rs.getInt(5),
                                       null
                    );
                }
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
        additionalShipTypes.forEach(item -> {
            jdbcTemplate.update("INSERT INTO product_ship_type(product_id, ship_type_id) VALUES (?,?)",
                                productId, item);
        });
        return getById(productId);
    }

    @Override
    public Product removeShipTypes(Integer productId, List<Integer> deductionShipTypes) {
        deductionShipTypes.forEach(item -> {
            jdbcTemplate.update("DELETE FROM product_ship_type WHERE product_id = ? AND ship_type_id = ?",
                                productId, item);
        });
        return getById(productId);
    }
}
