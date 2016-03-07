package ru.aolisov.shoppingserver.data.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.aolisov.shoppingserver.data.entity.Product;

import java.util.List;

/**
 * Created by Alex on 2/23/2016.
 */

@Repository("productDao")
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Product get(long id) {
        return sessionFactory.getCurrentSession().get(Product.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> get() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class);
        return (List<Product>) criteria.list();
    }

    @Override
    public void save(Product product) {
        sessionFactory.getCurrentSession().persist(product);
    }

    @Override
    public void delete(Product product) {
        sessionFactory.getCurrentSession().delete(product);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
