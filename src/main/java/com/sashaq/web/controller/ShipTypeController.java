package com.sashaq.web.controller;

import com.sashaq.entity.ShipType;
import com.sashaq.service.bean.ShipTypeService;
import com.sashaq.web.rq.ShipTypeCreateRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ship-type")
public class ShipTypeController {
    private final ShipTypeService shipTypeService;

    public ShipTypeController(ShipTypeService shipTypeService) {
        this.shipTypeService = shipTypeService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ShipType create(@Validated @RequestBody ShipTypeCreateRequest request) {
        return shipTypeService.create(request.getName(), request.getCost());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ShipType update(@RequestBody ShipType request) {
        return shipTypeService.update(request);
    }

    @RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
    public ShipType getById(@PathVariable Integer userId) {
        return shipTypeService.getById(userId);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<ShipType> getById() {
        return shipTypeService.getAll();
    }
}
