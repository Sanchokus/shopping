package ru.aolisov.shoppingserver.data.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aolisov.shoppingserver.data.dao.ProductDao;
import ru.aolisov.shoppingserver.data.entity.Product;
import java.util.List;

/**
 * Created by Alex on 2/23/2016.
 */

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductDao dao;

    @Override
    public Product findById(long id) {
        logger.debug("get by id: " + id);
        return dao.get(id);
    }

    @Override
    public void save(Product product) {
        logger.info("save: " + product);
        dao.save(product);
    }

    @Override
    public void update(Product product) {
        logger.info("update: " + product);
        Product entity = dao.get(product.getId());
        if(entity != null) {
            entity.setName(product.getName());
        }
    }

    @Override
    public void delete(Product product) {
        logger.info("delete: " + product);
        dao.delete(product);
    }

    @Override
    public List<Product> findAll() {
        logger.debug("find all");
        return dao.get();
    }
}
