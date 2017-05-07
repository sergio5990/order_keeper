package com.sashaq.web.rs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInOrderResponse {
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private Integer shipTypeId;
    private Float productPrice;
    private Integer productQantity;
    private Float shipPrice;

    public ProductInOrderResponse(Integer id,
                          Integer orderId,
                          Integer productId,
                          Integer shipTypeId,
                          Float productPrice,
                          Integer productQantity,
                          Float shipPrice) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.shipTypeId = shipTypeId;
        this.productPrice = productPrice;
        this.productQantity = productQantity;
        this.shipPrice = shipPrice;
    }
}
