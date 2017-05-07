package com.sashaq.dao.impl;

import com.sashaq.dao.OrderDao;
import com.sashaq.entity.Order;
import com.sashaq.entity.ProductInOrder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final JdbcTemplate jdbcTemplate;

    public OrderDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Order createOrder(Order newOrder) {
        SimpleJdbcInsert productInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("customer_order")
                .usingGeneratedKeyColumns("id");
        Number key = productInsert.executeAndReturnKey(
                new MapSqlParameterSource()
                        .addValue("creator_id", newOrder.getCreatorId())
                        .addValue("creation_date", LocalDateTime.now()));
        final Integer newId = key.intValue();

        return jdbcTemplate.queryForObject(
                "SELECT * FROM customer_order WHERE id = ?",
                new Object[]{newId},
                (rs, rowNum) -> new Order(rs.getInt("id"),
                                          rs.getInt("creator_id"),
                                          rs.getTimestamp("creation_date").toLocalDateTime(),
                                          null
                )
        );
    }

    @Override
    public List<ProductInOrder> addProductsInOrder(List<ProductInOrder> productsInOrder) {
        List<Object[]> collect = productsInOrder.stream()
                                                .map(productInOrder -> new Object[]{productInOrder.getOrderId(),
                                                        productInOrder.getProductId(),
                                                        productInOrder.getShipTypeId(),
                                                        productInOrder.getProductPrice(),
                                                        productInOrder.getProductQantity(),
                                                        productInOrder.getShipPrice()
                                                })
                                                .collect(Collectors.toList());

        jdbcTemplate.batchUpdate(
                "INSERT INTO products_in_order(order_id, product_id, ship_type_id, product_price, product_quantity, ship_price) VALUES (?,?,?,?,?,?)",
                collect);

        return jdbcTemplate.query(
                "SELECT * FROM products_in_order WHERE order_id = ?",
                new Object[]{productsInOrder.get(0).getOrderId()},
                (rs, rowNum) -> new ProductInOrder(rs.getInt("id"),
                                                   rs.getInt("order_id"),
                                                   rs.getInt("product_id"),
                                                   rs.getInt("ship_type_id"),
                                                   rs.getFloat("product_price"),
                                                   rs.getInt("product_quantity"),
                                                   rs.getFloat("ship_price"))
        );
    }
}
