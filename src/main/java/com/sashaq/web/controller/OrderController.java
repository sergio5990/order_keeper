package com.sashaq.web.controller;

import com.sashaq.entity.Company;
import com.sashaq.entity.CustomerOrder;
import com.sashaq.entity.ProductInOrder;
import com.sashaq.security.entity.SecurityUser;
import com.sashaq.service.bean.CompanyService;
import com.sashaq.service.bean.OrderService;
import com.sashaq.service.builder.OrderBuilder;
import com.sashaq.service.builder.ProductInOrderBuilder;
import com.sashaq.web.rq.CreateOrderRequest;
import com.sashaq.web.rs.OrderCreationResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.toIntExact;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final CompanyService companyService;

    public OrderController(final OrderService orderService, CompanyService companyService) {
        this.orderService = orderService;
        this.companyService = companyService;
    }

    @PostMapping("/create")
    public OrderCreationResponse createOrder(@Validated @RequestBody CreateOrderRequest request,
                                             @AuthenticationPrincipal SecurityUser activeUser) {
        List<ProductInOrder> productsInOrder = request.getProductsInOrder()
                                                      .stream()
                                                      .map(ProductInOrderBuilder::fromRequest)
                                                      .collect(Collectors.toList());

        Company companyId = companyService.getCompanyByUserId(toIntExact(activeUser.getId()));
        CustomerOrder newCustomerOrder = new OrderBuilder().creatorId(companyId.getId())
                                                           .creationDateIsNow()
                                                           .productsInOrder(productsInOrder)
                                                           .build();

        return new OrderCreationResponse(orderService.save(newCustomerOrder));
    }
}
