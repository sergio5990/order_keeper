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

import static com.sashaq.core.util.constant.StringConstant.PRODUCT_ID;


@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public Product login(@Validated @RequestBody ProductCreateRequest request) {
        Product newProduct = ProductBuilder.fromRequest(request);

        return productService.create(newProduct);
    }

    @GetMapping("/list")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{productId}")
    public Product getById(@PathVariable(PRODUCT_ID) Integer productId) {

        return productService.getById(productId);
    }

    @PostMapping("/{productId}/add-quantity")
    public Product addQuantity(@PathVariable(PRODUCT_ID) Integer productId,
                               @Validated @RequestBody AddQuantityRequest request) {
        productService.addQuantity(productId, request.getAdditionalQuantity());

        return productService.getById(productId);
    }

    @PostMapping("/{productId}/add-ship-types")
    public Product addShipTypes(@PathVariable(PRODUCT_ID) Integer productId,
                                @Validated @RequestBody ShipTypeIdsRequest request) {
        productService.addShipTypes(productId, request.getShipTypeIds());

        return productService.getById(productId);
    }

    @PostMapping("/{productId}/remove-ship-types")
    public Product removeShipTypes(@PathVariable(PRODUCT_ID) Integer productId,
                                   @Validated @RequestBody ShipTypeIdsRequest request) {
        productService.removeShipTypes(productId, request.getShipTypeIds());

        return productService.getById(productId);
    }
}
