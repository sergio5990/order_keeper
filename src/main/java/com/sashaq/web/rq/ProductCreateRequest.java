package com.sashaq.web.rq;

import com.sashaq.entity.ShipType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
public class ProductCreateRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Range
    private Float price;

    @Range
    private Integer quantity;

    @NotEmpty
    private List<Integer> shipTypes;

    public List<ShipType> mapShipTypes(){
        return shipTypes.stream()
                        .map(ShipType::new)
                        .collect(toList());
    }
}
