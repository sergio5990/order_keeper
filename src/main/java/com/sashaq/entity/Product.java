package com.sashaq.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Product {
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private Integer quantity;
    private List<ShipType> shipTypes;

    public Product(){

    }

    public Product(Integer id, String name, String description, Float price, Integer quantity, List<ShipType> shipTypes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.shipTypes = shipTypes;
    }
}
