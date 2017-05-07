package com.sashaq.dao;

import com.sashaq.entity.ShipType;

import java.util.List;

public interface ShipTypeDao {
    int save(String name, Float cost);

    int update(Integer id, String name, Float cost);

    ShipType getById(Integer shipTypeId);

    List<ShipType> getAll();
}
