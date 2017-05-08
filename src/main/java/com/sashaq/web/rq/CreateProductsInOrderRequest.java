package com.sashaq.web.rq;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateProductsInOrderRequest {
    @NotNull
    @Range
    private Integer productId;

    @NotNull
    @Range
    private Integer shipTypeId;

    @NotNull
    @Range
    private Integer productQuantity;
}
