package com.sashaq.web.controller;

import com.sashaq.entity.Order;
import com.sashaq.entity.ProductInOrder;
import com.sashaq.service.bean.OrderService;
import com.sashaq.service.builder.OrderBuilder;
import com.sashaq.service.builder.ProductInOrderBuilder;
import com.sashaq.web.rq.CreateOrderRequest;
import com.sashaq.web.rs.OrderCreationResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public OrderCreationResponse createOrder(@Validated @RequestBody CreateOrderRequest request) {
        List<ProductInOrder> productsInOrder = request.getProductsInOrder()
                                                      .stream()
                                                      .map(ProductInOrderBuilder::fromRequest)
                                                      .collect(Collectors.toList());

        Order newOrder = new OrderBuilder().creatorId(request.getCreatorId())
                                           .creationDateIsNow()
                                           .productsInOrder(productsInOrder)
                                           .build();

        return new OrderCreationResponse(orderService.save(newOrder));
    }
}
