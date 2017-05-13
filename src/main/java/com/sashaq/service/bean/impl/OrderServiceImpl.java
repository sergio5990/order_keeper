package com.sashaq.service.bean.impl;

import com.sashaq.dao.OrderDao;
import com.sashaq.entity.*;
import com.sashaq.service.bean.OrderService;
import com.sashaq.service.bean.ProductService;
import com.sashaq.service.bean.ShipTypeService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final ProductService productService;
    private final ShipTypeService shipTypeService;
    private final OrderDao orderDao;

    public OrderServiceImpl(final ProductService productService, final ShipTypeService shipTypeService, final OrderDao orderDao) {
        this.productService = productService;
        this.shipTypeService = shipTypeService;
        this.orderDao = orderDao;
    }

    @Override
    @Transactional
    //todo review
    public CustomerOrder save(CustomerOrder newCustomerOrder) {
        CustomerOrder createdCustomerOrder = orderDao.createOrder(newCustomerOrder);

        List<ProductInOrder> rawProducts = newCustomerOrder.getProductsInOrder();
        rawProducts.forEach(rawProduct -> {
            Product fullProduct = productService.getById(rawProduct.getProduct().getId());
            ShipType fullShipType = shipTypeService.getById(rawProduct.getShipType().getId());
            rawProduct.setShipPrice(fullShipType.getCost());
            rawProduct.setProductPrice(fullProduct.getPrice());
            rawProduct.setOrderId(createdCustomerOrder.getId());
        });

        List<ProductInOrder> addedProducts = orderDao.addProductsInOrder(rawProducts);
        createdCustomerOrder.setProductsInOrder(addedProducts);

        return createdCustomerOrder;
    }

    @Override
    public List<CustomerOrder> getCompanyOrders(Integer companyId) {
        List<CustomerOrder> orders = orderDao.getCompanyOrders(companyId);
        orders.forEach(order -> {
            order.setProductsInOrder(orderDao.getProductsInOrder(order.getId()));
        });
        return orders;
    }
}
