package com.sashaq.service.bean;

import com.sashaq.entity.Company;
import com.sashaq.entity.CustomerOrder;

import java.util.List;

public interface OrderService {
    CustomerOrder save(CustomerOrder newCustomerOrder);

    List<CustomerOrder> getCompanyOrders(Integer companyId);
}
