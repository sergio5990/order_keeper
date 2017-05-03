package com.sashaq.service.impl;

import com.sashaq.entity.Product;
import com.sashaq.entity.Shiptype;

import java.util.List;

public class ProductBuilder {
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private Integer quantity;
    private List<Shiptype> shipTypes;

    public ProductBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public ProductBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder price(Float price) {
        this.price = price;
        return this;
    }

    public ProductBuilder quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public ProductBuilder shipTypes(List<Shiptype> shipTypes) {
        this.shipTypes = shipTypes;
        return this;
    }

    public Product build(){
        return new Product(id, name, description, price, quantity, shipTypes);
    }
}
