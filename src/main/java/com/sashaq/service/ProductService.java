package com.sashaq.service;

import com.sashaq.entity.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);

    List<Product> getAll();

    Product getById(Integer productId);

    Product addQuantity(Integer productId, Integer additionalQuantity);

    Product addShipTypes(Integer productId, List<Integer> shipTypeIds);

    Product removeShipTypes(Integer productId, List<Integer> shipTypeIds);
}
