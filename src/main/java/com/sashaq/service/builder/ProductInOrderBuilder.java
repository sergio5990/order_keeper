package com.sashaq.service.builder;

import com.sashaq.entity.Product;
import com.sashaq.entity.ProductInOrder;
import com.sashaq.entity.ShipType;
import com.sashaq.web.rq.CreateProductsInOrderRequest;

public class ProductInOrderBuilder {
    private Integer id;
    private Integer orderId;
    private Product product;
    private ShipType shipType;
    private Float productPrice;
    private Integer productQuantity;
    private Float shipPrice;

    public ProductInOrderBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public ProductInOrderBuilder orderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public ProductInOrderBuilder product(Integer productId) {
        this.product = new Product(productId);
        return this;
    }

    public ProductInOrderBuilder product(Product product) {
        this.product = product;
        return this;
    }

    public ProductInOrderBuilder shipType(Integer shipTypeId) {
        this.shipType = new ShipType(shipTypeId);
        return this;
    }

    public ProductInOrderBuilder shipType(ShipType shipType) {
        this.shipType = shipType;
        return this;
    }

    public ProductInOrderBuilder productPrice(Float productPrice) {
        this.productPrice = productPrice;
        return this;
    }

    public ProductInOrderBuilder productQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
        return this;
    }

    public ProductInOrderBuilder shipPrice(Float shipPrice) {
        this.shipPrice = shipPrice;
        return this;
    }

    public ProductInOrder build() {
        return new ProductInOrder(id,
                                  orderId,
                                  product,
                                  shipType,
                                  productPrice,
                                  productQuantity,
                                  shipPrice);
    }

    public static ProductInOrder fromRequest(CreateProductsInOrderRequest request) {
        return new ProductInOrderBuilder().product(request.getProductId())
                                          .shipType(request.getShipTypeId())
                                          .productQuantity(request.getProductQuantity())
                                          .build();
    }
}
