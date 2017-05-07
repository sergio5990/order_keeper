package com.sashaq.service.impl;

import com.sashaq.dao.ProductDao;
import com.sashaq.entity.Product;
import com.sashaq.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    @Transactional
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product getById(Integer productId) {
        return productDao.getById(productId);
    }

    @Override
    @Transactional
    public void addQuantity(Integer productId, Integer additionalQuantity) {
         productDao.addQuantity(productId, additionalQuantity);
    }

    @Override
    public void addShipTypes(Integer productId, List<Integer> shipTypeIds) {
        productDao.addShipTypes(productId, shipTypeIds);
    }

    @Override
    @Transactional
    public void removeShipTypes(Integer productId, List<Integer> shipTypeIds) {
        productDao.removeShipTypes(productId, shipTypeIds);
    }
}
