package com.sashaq.web.rq;

import com.sashaq.entity.Shiptype;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    public List<Shiptype> mapShipTypes(){
        return shipTypes.stream().map(Shiptype::new).collect(Collectors.toList());
    }
}
