package com.sashaq.web.rq;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrderRequest {
    private Integer creatorId;
    private List<CreateProductsInOrderRequest> productsInOrder;
}
