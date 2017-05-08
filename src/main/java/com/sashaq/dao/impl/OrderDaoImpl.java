package com.sashaq.dao.impl;

import com.sashaq.dao.OrderDao;
import com.sashaq.entity.CustomerOrder;
import com.sashaq.entity.ProductInOrder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sashaq.core.util.constant.StringConstant.*;
import static java.time.LocalDateTime.now;
import static java.util.stream.Collectors.toList;

@Repository
public class OrderDaoImpl extends BaseDao<CustomerOrder> implements OrderDao {
    private static final RowMapper<CustomerOrder> ORDER_ROW_MAPPER = (rs, rowNum) -> new CustomerOrder(rs.getInt(ID),
                                                                                                       rs.getInt(CREATOR_ID),
                                                                                                       rs.getTimestamp(CREATION_DATE).toLocalDateTime(),
                                                                                                       null);

    public OrderDaoImpl(final JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, CUSTOMER_ORDER);
    }

    @Override
    public CustomerOrder createOrder(CustomerOrder newCustomerOrder) {
        Number key = getSimpleInsert().executeAndReturnKey(createParameterSource(newCustomerOrder));
        final Integer newId = key.intValue();

        return getJdbcTemplate().queryForObject("SELECT * FROM customer_order WHERE id = ?",
                                                params(newId),
                                                ORDER_ROW_MAPPER);
    }

    private static MapSqlParameterSource createParameterSource(final CustomerOrder newCustomerOrder) {
        return new MapSqlParameterSource().addValue(CREATOR_ID, newCustomerOrder.getCreatorId())
                                          .addValue(CREATION_DATE, now());
    }

    @Override
    public List<ProductInOrder> addProductsInOrder(List<ProductInOrder> productsInOrder) {
        List<Object[]> collect = productsInOrder.stream()
                                                .map(OrderDaoImpl::mapToArray)
                                                .collect(toList());

        getJdbcTemplate().batchUpdate(
                "INSERT INTO products_in_order(order_id, product_id, ship_type_id, product_price, product_quantity, ship_price) VALUES (?,?,?,?,?,?)",
                collect);

        return getJdbcTemplate().query(
                "SELECT * FROM products_in_order WHERE order_id = ?",
                params(productsInOrder.get(0).getOrderId()),
                (rs, rowNum) -> new ProductInOrder(rs.getInt("id"),
                                                   rs.getInt("order_id"),
                                                   rs.getInt("product_id"),
                                                   rs.getInt("ship_type_id"),
                                                   rs.getFloat("product_price"),
                                                   rs.getInt("product_quantity"),
                                                   rs.getFloat("ship_price"))
                                      );
    }

    private static Object[] mapToArray(ProductInOrder p) {
        return new Object[]{p.getOrderId(),
                            p.getProductId(),
                            p.getShipTypeId(),
                            p.getProductPrice(),
                            p.getProductQuantity(),
                            p.getShipPrice()};
    }
}
