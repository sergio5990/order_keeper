package com.sashaq.service;

import com.sashaq.entity.Shiptype;

import java.util.List;

public interface ShipTypeService {
    Shiptype create(String name, Float cost);

    Shiptype update(Integer id, String name, Float cost);

    Shiptype getById(Integer shipTypeId);

    List<Shiptype> getAll();
}
