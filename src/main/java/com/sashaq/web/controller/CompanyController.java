package com.sashaq.web.controller;

import com.sashaq.entity.Company;
import com.sashaq.service.CompanyService;
import com.sashaq.service.impl.CompanyBuilder;
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

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CompanyCreationResponse createCompany(@Validated @RequestBody CreateCompanyRequest request) {
        Company company = new CompanyBuilder().name(request.getName())
                                              .address(request.getAddress())
                                              .phone(request.getPhone())
                                              .contactUser(request.getContactUser())
                                              .build();

        return new CompanyCreationResponse(companyService.createCompany(company));
    }
}
