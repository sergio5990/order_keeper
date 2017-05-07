package com.sashaq.service.builder;

import com.sashaq.entity.Order;
import com.sashaq.entity.ProductInOrder;

import java.time.LocalDateTime;
import java.util.List;

public class OrderBuilder {
    private Integer id;
    private Integer creatorId;
    private LocalDateTime creationDate;
    private List<ProductInOrder> productsInOrder;

    public OrderBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public OrderBuilder creatorId(Integer creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    public OrderBuilder creationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public OrderBuilder creationDateIsNow() {
        this.creationDate = LocalDateTime.now();
        return this;
    }

    public OrderBuilder productsInOrder(List<ProductInOrder> productsInOrder) {
        this.productsInOrder = productsInOrder;
        return this;
    }

    public Order build() {
        return new Order(id, creatorId, creationDate, productsInOrder);
    }

}
