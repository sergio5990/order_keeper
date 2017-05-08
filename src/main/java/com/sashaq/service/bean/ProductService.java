package com.sashaq.service.bean;

import com.sashaq.entity.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);

    List<Product> getAll();

    Product getById(Integer productId);

    void addQuantity(Integer productId, Integer additionalQuantity);

    void addShipTypes(Integer productId, List<Integer> shipTypeIds);

    void removeShipTypes(Integer productId, List<Integer> shipTypeIds);
}
