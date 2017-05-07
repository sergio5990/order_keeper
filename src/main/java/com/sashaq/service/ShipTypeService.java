package com.sashaq.service;

import com.sashaq.entity.ShipType;

import java.util.List;

public interface ShipTypeService {
    ShipType create(String name, Float cost);

    ShipType update(ShipType shipType);

    ShipType getById(Integer shipTypeId);

    List<ShipType> getAll();
}
