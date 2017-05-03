package com.sashaq.web.controller;

import com.sashaq.entity.Product;
import com.sashaq.entity.Shiptype;
import com.sashaq.service.ProductService;
import com.sashaq.service.impl.ProductBuilder;
import com.sashaq.web.rq.AddQuantityRequest;
import com.sashaq.web.rq.LoginRequest;
import com.sashaq.web.rq.ProductCreateRequest;
import com.sashaq.web.rq.ShipTypeIdListRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Product login(@Validated @RequestBody ProductCreateRequest request) {
        Product newProduct = new ProductBuilder().name(request.getName())
                                                 .description(request.getDescription())
                                                 .price(request.getPrice())
                                                 .quantity(request.getQuantity())
                                                 .shipTypes(request.mapShipTypes())
                                                 .build();
        return productService.create(newProduct);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Product> getAll() {
        return productService.getAll();
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public Product getById(@PathVariable Integer productId) {
        return productService.getById(productId);
    }

    @RequestMapping(value = "/{productId}/add-quantity", method = RequestMethod.POST)
    public Product addQuantity(@PathVariable Integer productId,
                                @Validated @RequestBody AddQuantityRequest request) {
        return productService.addQuantity(productId, request.getAdditionalQuantity());
    }

    @RequestMapping(value = "/{productId}/add-ship-types", method = RequestMethod.POST)
    public Product addShipTypes(@PathVariable Integer productId,
                               @Validated @RequestBody ShipTypeIdListRequest request) {
        return productService.addShipTypes(productId, request.getShipTypeIds());
    }

    @RequestMapping(value = "/{productId}/remove-ship-types", method = RequestMethod.POST)
    public Product removeShipTypes(@PathVariable Integer productId,
                               @Validated @RequestBody ShipTypeIdListRequest request) {
        return productService.removeShipTypes(productId, request.getShipTypeIds());
    }
}
