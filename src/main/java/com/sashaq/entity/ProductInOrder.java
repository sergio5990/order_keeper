package com.sashaq.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInOrder {
    private Integer id;
    private Integer orderId;
    private Product product;
    private ShipType shipType;
    private Float productPrice;
    private Integer productQuantity;
    private Float shipPrice;

    public ProductInOrder(Integer id,
                          Integer orderId,
                          Product product,
                          ShipType shipType,
                          Float productPrice,
                          Integer productQuantity,
                          Float shipPrice) {
        this.id = id;
        this.orderId = orderId;
        this.product = product;
        this.shipType = shipType;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.shipPrice = shipPrice;
    }
}
