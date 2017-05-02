package com.sashaq.web.rq;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
public class ShipTypeCreateRequest {
    @NotBlank
    private String name;
    private Float cost;
}
