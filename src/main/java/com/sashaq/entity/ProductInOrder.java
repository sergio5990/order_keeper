package com.sashaq.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInOrder {
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private Integer shipTypeId;
    private Float productPrice;
    private Integer productQuantity;
    private Float shipPrice;

    public ProductInOrder(Integer id,
                          Integer orderId,
                          Integer productId,
                          Integer shipTypeId,
                          Float productPrice,
                          Integer productQuantity,
                          Float shipPrice) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.shipTypeId = shipTypeId;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.shipPrice = shipPrice;
    }
}
