package com.sashaq.web.rs;

import com.sashaq.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
public class OrderCreationResponse {
    private Integer id;
    private Integer creatorId;
    private LocalDateTime creationDate;
    private List<ProductInOrderResponse> productsInOrder;

    public OrderCreationResponse(final Order order) {
        this.id = order.getId();
        this.creatorId = order.getCreatorId();
        this.creationDate = order.getCreationDate();
        this.productsInOrder = order.getProductsInOrder()
                                    .stream()
                                    .map(ProductInOrderResponse::new)
                                    .collect(toList());
    }
}
