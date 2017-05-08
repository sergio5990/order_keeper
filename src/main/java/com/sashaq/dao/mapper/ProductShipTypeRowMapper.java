package com.sashaq.dao.mapper;

import com.sashaq.entity.Product;
import com.sashaq.entity.ShipType;
import org.apache.commons.collections.MapUtils;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.sashaq.core.util.constant.StringConstant.*;
import static java.util.Collections.*;

public class ProductShipTypeRowMapper implements ResultSetExtractor<List<Product>> {
    private static final ProductShipTypeRowMapper MAPPER = new ProductShipTypeRowMapper();
    private static final String P_ID = "p_id";
    private static final String P_NAME = "p_name";

    private ProductShipTypeRowMapper() {
    }

    public static ProductShipTypeRowMapper getInstance() {
        return MAPPER;
    }

    @Override
    public List<Product> extractData(final ResultSet rs) throws SQLException {
        Map<Integer, Product> productById = new HashMap<>();
        while (rs.next()) {
            int productId = rs.getInt(P_ID);
            Product product = productById.get(productId);
            if (product == null) {
                product = createProduct(rs);
                productById.put(productId, product);
            }
            ShipType shipType = createShipType(rs);
            product.getShipTypes().add(shipType);
        }

        if (MapUtils.isEmpty(productById)) {
            return emptyList();
        } else {
            return new ArrayList<>(productById.values());
        }
    }

    private ShipType createShipType(final ResultSet rs) throws SQLException {
        ShipType shipType = new ShipType(rs.getInt(ID));
        shipType.setName(rs.getString(NAME));
        shipType.setCost(rs.getFloat(COST));

        return shipType;
    }

    private static Product createProduct(final ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt(P_ID));
        product.setName(rs.getString(P_NAME));
        product.setDescription(rs.getString(DESCRIPTION));
        product.setPrice(rs.getFloat(PRICE));
        product.setQuantity(rs.getInt(QUANTITY));
        product.setShipTypes(new ArrayList<>());

        return product;
    }
}
