package com.sashaq.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipType {
    private Integer id;
    private String name;
    private Float cost;

    public ShipType(){}

    public ShipType(Integer id, String name, Float cost){
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public ShipType(Integer id) {
        this.id = id;
    }


}
