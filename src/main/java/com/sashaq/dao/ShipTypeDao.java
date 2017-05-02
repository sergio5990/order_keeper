package com.sashaq.dao;

import com.sashaq.entity.Shiptype;

import java.util.List;

public interface ShipTypeDao {
    Shiptype create(String name, Float cost);

    Shiptype update(Integer id, String name, Float cost);

    Shiptype getById(Integer shipTypeId);

    List<Shiptype> getAll();
}
