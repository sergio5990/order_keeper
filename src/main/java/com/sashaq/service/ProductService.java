package com.sashaq.service;

import com.sashaq.entity.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);

    List<Product> getAll();

    Product getById(Integer productId);

    void addQuantity(Integer productId, Integer additionalQuantity);

    void addShipTypes(Integer productId, List<Integer> shipTypeIds);

    void removeShipTypes(Integer productId, List<Integer> shipTypeIds);
}
