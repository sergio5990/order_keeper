package com.sashaq.dao;

import com.sashaq.entity.Order;
import com.sashaq.entity.ProductInOrder;

import java.util.List;

public interface OrderDao {
    Order createOrder(Order newOrder);

    List<ProductInOrder> addProductsInOrder(List<ProductInOrder> rawProducts);
}
