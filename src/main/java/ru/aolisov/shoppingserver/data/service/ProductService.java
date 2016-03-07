package ru.aolisov.shoppingserver.data.service;

import ru.aolisov.shoppingserver.data.entity.Product;

import java.util.List;

/**
 * Created by Alex on 2/23/2016.
 */
public interface ProductService {

    Product findById(long id);
    void save(Product product);
    void update(Product product);
    void delete(Product product);
    List<Product> findAll();
}
