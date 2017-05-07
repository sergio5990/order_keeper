package com.sashaq.web.rs;

import com.sashaq.entity.ProductInOrder;
import lombok.Getter;

@Getter
public class ProductInOrderResponse {
    private final Integer id;
    private final Integer orderId;
    private final Integer productId;
    private final Integer shipTypeId;
    private final Float productPrice;
    private final Integer productQantity;
    private final Float shipPrice;

    public ProductInOrderResponse(final ProductInOrder product) {
        this.id = product.getId();
        this.orderId = product.getOrderId();
        this.productId = product.getProductId();
        this.shipTypeId = product.getShipTypeId();
        this.productPrice = product.getProductPrice();
        this.productQantity = product.getProductQantity();
        this.shipPrice = product.getShipPrice();
    }
}
