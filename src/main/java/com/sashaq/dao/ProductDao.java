package com.sashaq.dao;

import com.sashaq.entity.Product;
import com.sashaq.entity.Shiptype;

import java.util.List;

public interface ProductDao {
    Product create(Product product);

    Integer getIdByName(String name);

    List<Product> getAll();

    Product getById(Integer productId);

    List<Shiptype> getShipTypesInProduct(Integer productId);

    Product addQuantity(Integer productId, Integer additionalQuantity);

    Product addShipTypes(Integer productId, List<Integer> additionalShipTypes);

    Product removeShipTypes(Integer productId, List<Integer> deductionShipTypes);
}
