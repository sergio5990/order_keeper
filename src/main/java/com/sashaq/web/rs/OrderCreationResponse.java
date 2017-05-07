package com.sashaq.web.rs;

import com.sashaq.entity.Order;
import com.sashaq.entity.ProductInOrder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class OrderCreationResponse {
    private Integer id;
    private Integer creatorId;
    private Timestamp creationDate;
    private List<ProductInOrderResponse> productsInOrder;

    public OrderCreationResponse(Integer id, Integer creatorId, LocalDateTime creationDate, List<ProductInOrder> productsInOrder) {
        this.id = id;
        this.creatorId = creatorId;
        this.creationDate = Timestamp.valueOf(creationDate);
        this.productsInOrder = productsInOrder.stream()
                                              .map(productInOrder -> new ProductInOrderResponse(
                                                      productInOrder.getId(),
                                                      productInOrder.getOrderId(),
                                                      productInOrder.getProductId(),
                                                      productInOrder.getShipTypeId(),
                                                      productInOrder.getProductPrice(),
                                                      productInOrder.getProductQantity(),
                                                      productInOrder.getShipPrice()
                                                   )
                                              )
                                              .collect(Collectors.toList());
    }

    public OrderCreationResponse(Order order) {
        this.id = order.getId();
        this.creatorId = order.getCreatorId();
        this.creationDate = Timestamp.valueOf(order.getCreationDate());
        this.productsInOrder = order.getProductsInOrder().stream()
                                              .map(productInOrder -> new ProductInOrderResponse(
                                                           productInOrder.getId(),
                                                           productInOrder.getOrderId(),
                                                           productInOrder.getProductId(),
                                                           productInOrder.getShipTypeId(),
                                                           productInOrder.getProductPrice(),
                                                           productInOrder.getProductQantity(),
                                                           productInOrder.getShipPrice()
                                                   )
                                              )
                                              .collect(Collectors.toList());
    }
}
