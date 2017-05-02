package com.sashaq.service.impl;

import com.sashaq.dao.ShipTypeDao;
import com.sashaq.entity.Shiptype;
import com.sashaq.service.ShipTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShipTypeServiceImpl implements ShipTypeService {
    private final ShipTypeDao shipTypeDao;

    public ShipTypeServiceImpl(ShipTypeDao shipTypeDao) {
        this.shipTypeDao = shipTypeDao;
    }

    @Override
    @Transactional
    public Shiptype create(String name, Float cost) {
        return shipTypeDao.create(name, cost);
    }

    @Override
    @Transactional
    public Shiptype update(Integer id, String name, Float cost) {
        return shipTypeDao.update(id, name, cost);
    }

    @Override
    @Transactional(readOnly = true)
    public Shiptype getById(Integer shipTypeId) {
        return shipTypeDao.getById(shipTypeId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Shiptype> getAll() {
        return shipTypeDao.getAll();
    }
}
