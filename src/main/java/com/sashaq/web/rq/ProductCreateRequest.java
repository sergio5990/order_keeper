package com.sashaq.web.rq;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

@Getter
@Setter
public class ProductCreateRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private Float price;
    private Integer quantity;

    private List<Integer> shipTypes;
}
