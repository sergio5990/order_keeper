package com.sashaq.dao;

import com.sashaq.entity.Product;
import com.sashaq.entity.ShipType;

import java.util.List;

public interface ProductDao {
    Product create(Product product);

    Integer getIdByName(String name);

    List<Product> getAll();

    Product getById(Integer productId);

    List<ShipType> getShipTypesInProduct(Integer productId);

    void addQuantity(Integer productId, Integer additionalQuantity);

    void addShipTypes(Integer productId, List<Integer> additionalShipTypes);

    void removeShipTypes(Integer productId, List<Integer> deductionShipTypes);
}
