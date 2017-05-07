package com.sashaq.web.rq;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ShipTypeCreateRequest {
    @NotBlank
    private String name;

    @NotNull
    @Range
    private Float cost;
}
