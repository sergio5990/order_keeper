package com.sashaq.service.bean.impl;

import com.sashaq.dao.ShipTypeDao;
import com.sashaq.entity.ShipType;
import com.sashaq.service.bean.ShipTypeService;
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
    public ShipType save(String name, Float cost) {
        int shipTypeId = shipTypeDao.save(name, cost);

        return new ShipType(shipTypeId, name, cost);
    }

    @Override
    @Transactional
    public ShipType update(final ShipType shipType) {
        shipTypeDao.update(shipType.getId(), shipType.getName(), shipType.getCost());

        return shipType;
    }

    @Override
    @Transactional(readOnly = true)
    public ShipType getById(Integer shipTypeId) {
        return shipTypeDao.getById(shipTypeId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShipType> getAll() {
        return shipTypeDao.getAll();
    }
}
