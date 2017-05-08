package com.sashaq.dao;

import com.sashaq.entity.CustomerOrder;
import com.sashaq.entity.ProductInOrder;

import java.util.List;

public interface OrderDao {
    CustomerOrder createOrder(CustomerOrder newCustomerOrder);

    List<ProductInOrder> addProductsInOrder(List<ProductInOrder> rawProducts);
}
