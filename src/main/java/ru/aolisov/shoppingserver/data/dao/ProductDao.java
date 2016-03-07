package ru.aolisov.shoppingserver.data.dao;

import ru.aolisov.shoppingserver.data.entity.Product;

import java.util.List;

/**
 * Created by Alex on 2/23/2016.
 */
public interface ProductDao {
    Product get(long id);
    List<Product> get();
    void save(Product product);
    void delete(Product product);
}
