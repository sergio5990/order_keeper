package com.sashaq.service.bean;

import com.sashaq.entity.CustomerOrder;

public interface OrderService {
    CustomerOrder save(CustomerOrder newCustomerOrder);
}
