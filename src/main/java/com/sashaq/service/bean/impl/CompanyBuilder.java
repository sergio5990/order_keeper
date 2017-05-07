package com.sashaq.service.bean.impl;

import com.sashaq.entity.Company;
import com.sashaq.entity.User;
import com.sashaq.service.bean.UserService;

public class CompanyBuilder {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private Integer contactUserId;

    private final UserService userService;

    public CompanyBuilder(final UserService userService) {
        this.userService = userService;
    }

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

    public CompanyBuilder contactUser(Integer contactUserId) {
        this.contactUserId = contactUserId;
        return this;
    }

    public Company build(){
        User contactUser = userService.getById(contactUserId);

        return new Company(id, name, address, phone, contactUser);
    }
}
