package com.sashaq.web.controller;

import com.sashaq.entity.ShipType;
import com.sashaq.service.bean.ShipTypeService;
import com.sashaq.web.rq.ShipTypeCreateRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sashaq.core.util.constant.StringConstant.SHIP_TYPE_ID;

@RestController
@RequestMapping("/ship-type")
public class ShipTypeController {
    private final ShipTypeService shipTypeService;

    public ShipTypeController(final ShipTypeService shipTypeService) {
        this.shipTypeService = shipTypeService;
    }

    @PostMapping("/create")
    public ShipType create(@Validated @RequestBody ShipTypeCreateRequest request) {
        return shipTypeService.save(request.getName(), request.getCost());
    }

    @PostMapping("/update")
    public ShipType update(@RequestBody ShipType request) {

        return shipTypeService.update(request);
    }

    @GetMapping("/{shipTypeId}")
    public ShipType getById(@PathVariable(SHIP_TYPE_ID) Integer shipTypeId) {

        return shipTypeService.getById(shipTypeId);
    }

    @GetMapping("/list")
    public List<ShipType> getAll() {

        return shipTypeService.getAll();
    }
}
