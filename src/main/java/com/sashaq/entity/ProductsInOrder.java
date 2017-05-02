package com.sashaq.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductsInOrder {
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private Integer shipTypeId;
    private Float productPrice;
    private Integer productQantity;
    private Float shipPrice;
}
