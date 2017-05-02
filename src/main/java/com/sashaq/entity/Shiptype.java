package com.sashaq.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Shiptype {
    private Integer id;
    private String name;
    private Float cost;

    public Shiptype(){};

    public Shiptype(Integer id, String name, Float cost){
        this.id = id;
        this.name = name;
        this.cost = cost;
    };


}
