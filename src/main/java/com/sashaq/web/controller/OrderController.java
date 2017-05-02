package com.sashaq.web.controller;

import com.sashaq.service.OrderService;
import com.sashaq.web.rq.CreateOrderRequest;
import com.sashaq.web.rq.OrderCreationResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public OrderCreationResponse createOrder(@Validated @RequestBody CreateOrderRequest request){
        return new OrderCreationResponse();
    }
}
