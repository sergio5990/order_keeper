package com.sashaq.service.impl;

import com.sashaq.dao.CompanyDao;
import com.sashaq.entity.Company;
import com.sashaq.entity.User;
import com.sashaq.service.CompanyService;
import com.sashaq.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyDao companyDao;
    private final UserService userService;

    public CompanyServiceImpl(CompanyDao companyDao, UserService userService) {
        this.companyDao = companyDao;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Company createCompany(Company company) {
        User contactUser = getOrCreateUser(company);
        company.setContactUser(contactUser);

        int companyId = companyDao.createCompany(company);
        company.setId(companyId);

        return company;
    }

    private User getOrCreateUser(final Company company) {
        User contactUser = company.getContactUser();
        Integer userId = contactUser.getId();

        if (userId == null) {
            contactUser = userService.create(contactUser);
        } else {
            contactUser = userService.getById(userId);
        }
        return contactUser;
    }
}
