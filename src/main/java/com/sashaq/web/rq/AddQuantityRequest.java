package com.sashaq.web.rq;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddQuantityRequest {
    @NotNull
    @Range
    private Integer additionalQuantity;
}
