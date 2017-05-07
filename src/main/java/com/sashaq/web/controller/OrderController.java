package com.sashaq.web.controller;

import com.sashaq.entity.Order;
import com.sashaq.entity.ProductInOrder;
import com.sashaq.service.bean.OrderService;
import com.sashaq.service.builder.OrderBuilder;
import com.sashaq.service.builder.ProductInOrderBuilder;
import com.sashaq.web.rq.CreateOrderRequest;
import com.sashaq.web.rs.OrderCreationResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public OrderCreationResponse createOrder(@Validated @RequestBody CreateOrderRequest request) {
        List<ProductInOrder> productsInOrder = request.getProductsInOrder()
                                                      .stream()
                                                      .map(productInOrder ->
                                                                   new ProductInOrderBuilder()
                                                                           .productId(productInOrder.getProductId())
                                                                           .shipTypeId(productInOrder.getShipTypeId())
                                                                           .productQantity(productInOrder.getProductQantity())
                                                                           .build()
                                                      )
                                                      .collect(Collectors.toList());

        Order newOrder = new OrderBuilder().creatorId(request.getCreatorId())
                                           .creationDateIsNow()
                                           .productsInOrder(productsInOrder)
                                           .build();

        return new OrderCreationResponse(orderService.create(newOrder));
    }
}
