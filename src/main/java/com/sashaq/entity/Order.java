package com.sashaq.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Order {
    private Integer id;
    private Integer creatorId;
    private LocalDateTime creationDate;
    private List<ProductsInOrder> productsInOrder;
}
