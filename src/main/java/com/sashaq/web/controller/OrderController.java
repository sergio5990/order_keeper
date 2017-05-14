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
import com.sashaq.web.rs.OrderResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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
    public OrderResponse createOrder(@Validated @RequestBody CreateOrderRequest request,
                                     @AuthenticationPrincipal SecurityUser activeUser) {
        List<ProductInOrder> productsInOrder = request.getProductsInOrder()
                                                      .stream()
                                                      .map(ProductInOrderBuilder::fromRequest)
                                                      .collect(Collectors.toList());

        Company companyId = companyService.getCompanyByUserId(activeUser.getId());
        CustomerOrder newCustomerOrder = new OrderBuilder().creatorId(companyId.getId())
                                                           .creationDateIsNow()
                                                           .productsInOrder(productsInOrder)
                                                           .build();

        return new OrderResponse(orderService.save(newCustomerOrder));
    }

    @GetMapping("/get-company-orders")
    public List<OrderResponse> getCompanyOrders(@AuthenticationPrincipal SecurityUser activeUser) {

        Company companyId = companyService.getCompanyByUserId(activeUser.getId());

        return orderService.getCompanyOrders(companyId.getId())
                           .stream()
                           .map(OrderResponse::new)
                           .collect(toList());
    }
}
