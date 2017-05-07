package com.sashaq.service.bean.impl;

import com.sashaq.dao.OrderDao;
import com.sashaq.entity.Order;
import com.sashaq.entity.Product;
import com.sashaq.entity.ProductInOrder;
import com.sashaq.entity.ShipType;
import com.sashaq.service.bean.OrderService;
import com.sashaq.service.bean.ProductService;
import com.sashaq.service.bean.ShipTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final ProductService productService;
    private final ShipTypeService shipTypeService;
    private final OrderDao orderDao;

    public OrderServiceImpl(ProductService productService, ShipTypeService shipTypeService, OrderDao orderDao) {
        this.productService = productService;
        this.shipTypeService = shipTypeService;
        this.orderDao = orderDao;
    }

    @Override
    @Transactional
    public Order create(Order newOrder) {
        Order createdOrder = orderDao.createOrder(newOrder);

        List<ProductInOrder> rawProducts = newOrder.getProductsInOrder();
        rawProducts.forEach(rawProduct -> {
            Product fullProduct = productService.getById(rawProduct.getProductId());
            ShipType fullShipType = shipTypeService.getById(rawProduct.getShipTypeId());
            rawProduct.setShipPrice(fullShipType.getCost());
            rawProduct.setProductPrice(fullProduct.getPrice());
            rawProduct.setOrderId(createdOrder.getId());
        });

        List<ProductInOrder> addedProducts = orderDao.addProductsInOrder(rawProducts);
        createdOrder.setProductsInOrder(addedProducts);

        return createdOrder;
    }
}
