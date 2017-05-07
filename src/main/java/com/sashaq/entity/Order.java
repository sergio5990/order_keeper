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
    private List<ProductInOrder> productsInOrder;

    public Order(Integer id, Integer creatorId, LocalDateTime creationDate, List<ProductInOrder> productsInOrder) {
        this.id = id;
        this.creatorId = creatorId;
        this.creationDate = creationDate;
        this.productsInOrder = productsInOrder;
    }
}
