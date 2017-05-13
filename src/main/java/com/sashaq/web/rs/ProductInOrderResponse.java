package com.sashaq.web.rs;

import com.sashaq.entity.Product;
import com.sashaq.entity.ProductInOrder;
import com.sashaq.entity.ShipType;
import lombok.Getter;
import org.springframework.security.access.method.P;

@Getter
public class ProductInOrderResponse {
    private final Integer id;
    private final Integer orderId;
    private final Product product;
    private final ShipType shipType;
    private final Float productPrice;
    private final Integer productQuantity;
    private final Float shipPrice;

    public ProductInOrderResponse(final ProductInOrder product) {
        this.id = product.getId();
        this.orderId = product.getOrderId();
        this.product = product.getProduct();
        this.shipType = product.getShipType();
        this.productPrice = product.getProductPrice();
        this.productQuantity = product.getProductQuantity();
        this.shipPrice = product.getShipPrice();
    }
}
