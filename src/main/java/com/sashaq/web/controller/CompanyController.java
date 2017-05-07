package com.sashaq.web.controller;

import com.sashaq.entity.Company;
import com.sashaq.service.bean.CompanyService;
import com.sashaq.service.bean.UserService;
import com.sashaq.service.bean.impl.CompanyBuilder;
import com.sashaq.web.rq.CreateCompanyRequest;
import com.sashaq.web.rs.CompanyCreationResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;
    private UserService userService;

    public CompanyController(CompanyService companyService, UserService userService) {
        this.companyService = companyService;
        this.userService = userService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CompanyCreationResponse createCompany(@Validated @RequestBody CreateCompanyRequest request) {
        Company company = new CompanyBuilder(userService).name(request.getName())
                                                         .address(request.getAddress())
                                                         .phone(request.getPhone())
                                                         .contactUser(request.getContactUserId())
                                                         .build();

        return new CompanyCreationResponse(companyService.createCompany(company));
    }
}
