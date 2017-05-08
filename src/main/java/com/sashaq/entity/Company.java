package com.sashaq.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Company {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private User contactUser;


    public Company() {
    }

    public Company(Integer id, String name, String address, String phone, User contactUser) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.contactUser = contactUser;
    }
}
