package com.sashaq.web.rs;

import com.sashaq.entity.Company;
import com.sashaq.entity.User;
import lombok.Getter;

@Getter
public class CompanyCreationResponse {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private UserResponse contactUser;

    public CompanyCreationResponse(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.address = company.getAddress();
        this.phone = company.getPhone();
        this.contactUser = new UserResponse(company.getContactUser());
    }
}
