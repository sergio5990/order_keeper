package com.sashaq.web.rq;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Getter
@Setter
public class ShipTypeIdsRequest {
    @NotEmpty
    private List<Integer> shipTypeIds;
}
