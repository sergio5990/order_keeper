package com.sashaq.service.impl;

import com.sashaq.entity.Company;
import com.sashaq.entity.User;

public class CompanyBuilder {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private User contactUser;

    public CompanyBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public CompanyBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CompanyBuilder address(String address) {
        this.address = address;
        return this;
    }

    public CompanyBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public CompanyBuilder contactUser(User contactUser) {
        this.contactUser = contactUser;
        return this;
    }

    public Company build(){
        return new Company(id, name, address, phone, contactUser);
    }
}
