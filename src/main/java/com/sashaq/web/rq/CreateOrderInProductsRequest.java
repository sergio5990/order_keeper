package com.sashaq.web.rq;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderInProductsRequest {
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private Integer shipTypeId;
    private Integer productQantity;
}
