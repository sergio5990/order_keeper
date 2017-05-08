package com.sashaq.web.rs;

import com.sashaq.entity.CustomerOrder;
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

    public OrderCreationResponse(final CustomerOrder customerOrder) {
        this.id = customerOrder.getId();
        this.creatorId = customerOrder.getCreatorId();
        this.creationDate = customerOrder.getCreationDate();
        this.productsInOrder = customerOrder.getProductsInOrder()
                                            .stream()
                                            .map(ProductInOrderResponse::new)
                                            .collect(toList());
    }
}
