package com.sashaq.web.rq;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CreateOrderRequest {
    @NotNull
    @Range
    private Integer creatorId;

    @NotEmpty
    @Valid
    private List<CreateProductsInOrderRequest> productsInOrder;
}
