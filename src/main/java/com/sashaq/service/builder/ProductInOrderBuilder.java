package com.sashaq.service.builder;

import com.sashaq.entity.ProductInOrder;
import com.sashaq.web.rq.CreateProductsInOrderRequest;

public class ProductInOrderBuilder {
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private Integer shipTypeId;
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

    public ProductInOrderBuilder productId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public ProductInOrderBuilder shipTypeId(Integer shipTypeId) {
        this.shipTypeId = shipTypeId;
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
                                  productId,
                                  shipTypeId,
                                  productPrice,
                                  productQuantity,
                                  shipPrice);
    }

    public static ProductInOrder fromRequest(CreateProductsInOrderRequest request) {
        return new ProductInOrderBuilder().productId(request.getProductId())
                                          .shipTypeId(request.getShipTypeId())
                                          .productQuantity(request.getProductQuantity())
                                          .build();
    }
}
