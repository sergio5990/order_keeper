package com.sashaq.web.rs;

import com.sashaq.entity.Company;
import lombok.Getter;

@Getter
public class CompanyCreationResponse {
    private final Integer id;
    private final String name;
    private final String address;
    private final String phone;
    private final UserResponse contactUser;

    public CompanyCreationResponse(final Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.address = company.getAddress();
        this.phone = company.getPhone();
        this.contactUser = new UserResponse(company.getContactUser());
    }
}
