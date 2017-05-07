package com.sashaq.web.rq;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductsInOrderRequest {
    private Integer productId;
    private Integer shipTypeId;
    private Integer productQantity;
}
