package com.sashaq.web.controller;

import com.sashaq.entity.Product;
import com.sashaq.service.bean.ProductService;
import com.sashaq.service.builder.ProductBuilder;
import com.sashaq.web.rq.AddQuantityRequest;
import com.sashaq.web.rq.ProductCreateRequest;
import com.sashaq.web.rq.ShipTypeIdsRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/create", method = POST)
    public Product login(@Validated @RequestBody ProductCreateRequest request) {
        Product newProduct = new ProductBuilder().name(request.getName())
                                                 .description(request.getDescription())
                                                 .price(request.getPrice())
                                                 .quantity(request.getQuantity())
                                                 .shipTypes(request.mapShipTypes())
                                                 .build();
        return productService.create(newProduct);
    }

    @RequestMapping(value = "/get", method = GET)
    public List<Product> getAll() {
        return productService.getAll();
    }

    @RequestMapping(value = "/{productId}", method = GET)
    public Product getById(@PathVariable Integer productId) {
        return productService.getById(productId);
    }

    @RequestMapping(value = "/{productId}/add-quantity", method = POST)
    public Product addQuantity(@PathVariable Integer productId,
                               @Validated @RequestBody AddQuantityRequest request) {
        productService.addQuantity(productId, request.getAdditionalQuantity());

        return productService.getById(productId);
    }

    @RequestMapping(value = "/{productId}/add-ship-types", method = POST)
    public Product addShipTypes(@PathVariable Integer productId,
                                @Validated @RequestBody ShipTypeIdsRequest request) {
        productService.addShipTypes(productId, request.getShipTypeIds());

        return productService.getById(productId);
    }

    @RequestMapping(value = "/{productId}/remove-ship-types", method = POST)
    public Product removeShipTypes(@PathVariable Integer productId,
                                   @Validated @RequestBody ShipTypeIdsRequest request) {
        productService.removeShipTypes(productId, request.getShipTypeIds());

        return productService.getById(productId);
    }
}
