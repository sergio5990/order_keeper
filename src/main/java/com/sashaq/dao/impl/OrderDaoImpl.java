package com.sashaq.dao.impl;

import com.sashaq.dao.OrderDao;
import com.sashaq.entity.CustomerOrder;
import com.sashaq.entity.Product;
import com.sashaq.entity.ProductInOrder;
import com.sashaq.entity.ShipType;
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
                                                                                                       rs.getInt(
                                                                                                               CREATOR_ID),
                                                                                                       rs.getTimestamp(
                                                                                                               CREATION_DATE)
                                                                                                         .toLocalDateTime(),
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

        return getProductsInOrder(productsInOrder.get(0).getOrderId());
    }

    @Override
    public List<ProductInOrder> getProductsInOrder(Integer orderId) {
        return getJdbcTemplate().query(
                "SELECT pio.id AS PIO_ID, pio.order_id AS PIO_OID, " +
                        "pio.product_price AS PIO_PP, pio.product_quantity AS PIO_PQ, " +
                        "pio.ship_price AS PIO_SP," +
                        "p.id AS P_ID, p.name AS P_NAME, p.description AS P_DESC, p.price AS P_PRICE, p.quantity  AS P_Q, " +
                        "st.id AS ST_ID, st.name AS ST_NAME, st.cost AS ST_COST " +
                        "FROM products_in_order pio " +
                        "JOIN product p ON pio.product_id = p.id " +
                        "JOIN ship_type st ON pio.ship_type_id = st.id " +
                        "WHERE pio.order_id = ?",
                params(orderId),
                (rs, rowNum) -> new ProductInOrder(rs.getInt("PIO_ID"),
                                                   rs.getInt("PIO_OID"),
                                                   new Product(
                                                           rs.getInt("P_ID"),
                                                           rs.getString("P_NAME"),
                                                           rs.getString("P_DESC")
                                                   ),
                                                   new ShipType(
                                                           rs.getInt("ST_ID"),
                                                           rs.getString("ST_NAME"),
                                                           rs.getFloat("ST_COST")
                                                   ),
                                                   rs.getFloat("PIO_PP"),
                                                   rs.getInt("PIO_PQ"),
                                                   rs.getFloat("PIO_SP"))
        );
    }

    @Override
    public List<CustomerOrder> getCompanyOrders(Integer companyId) {
        return getJdbcTemplate().query("SELECT * FROM customer_order WHERE creator_id = ?",
                                       params(companyId),
                                       ORDER_ROW_MAPPER);
    }

    private static Object[] mapToArray(ProductInOrder p) {
        return new Object[]{p.getOrderId(),
                p.getProduct().getId(),
                p.getShipType().getId(),
                p.getProductPrice(),
                p.getProductQuantity(),
                p.getShipPrice()};
    }
}
