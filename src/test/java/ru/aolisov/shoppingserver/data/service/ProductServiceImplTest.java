package ru.aolisov.shoppingserver.data.service;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.aolisov.shoppingserver.config.AppConfig;
import ru.aolisov.shoppingserver.data.entity.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alex on 3/6/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class ProductServiceImplTest {

    @Autowired
    private ProductService service;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void addProduct() {

        List<String> names = new ArrayList<>();
        names.add("test1");
        names.add("test2");
        names.add("тест3"); //cyrillic test

        for(String name: names) {
            Product product = new Product();
            product.setName(name);
            service.save(product);
        }

        List<Product> products = service.findAll();
        Assert.assertEquals(products.size(), names.size());
        for(Product product: products) {
            Assert.assertTrue(names.contains(product.getName()));
        }
    }

    @Test
    public void deleteProduct() {
        List<String> names = new ArrayList<>();
        names.add("test1");
        names.add("test2");

        for(String name: names) {
            Product product = new Product();
            product.setName(name);
            service.save(product);
        }
        List<Product> products = service.findAll();
        Product removableProduct = products.get(1);
        names.remove(removableProduct.getName());
        service.delete(removableProduct);

        products = service.findAll();
        assertProductsListByNames(products, names);
    }

    @Test
    public void deleteNonExistentProduct() {
        Product product = new Product();
        product.setName("opa");
        service.delete(product);
    }

    @Test
    public void editProduct() {
        List<String> names = new ArrayList<>();
        names.add("test1");
        names.add("test2");
        String anotherName = "newtest1";

        for(String name: names) {
            Product product = new Product();
            product.setName(name);
            service.save(product);
        }

        Product product = service.findAll().get(0);
        names.remove(product.getName());
        names.add(anotherName);
        product.setName(anotherName);
        service.update(product);

        List<Product> products = service.findAll();
        assertProductsListByNames(products, names);
    }

    @Test
    public void getProductById() {
        List<String> names = new ArrayList<>();
        names.add("test1");
        names.add("test2");

        for(String name: names) {
            Product product = new Product();
            product.setName(name);
            service.save(product);
        }

        Product product = service.findAll().get(0);
        Assert.assertEquals(product, service.findById(product.getId()));
    }

    private void assertProductsListByNames(List<Product> products, List<String> names) {
        List<String> productNames = new ArrayList<>();
        for(Product product: products) {
            productNames.add(product.getName());
        }

        Collections.sort(productNames);

        //because we don't want to modify list
        List<String> sortedNames = new ArrayList<>(names);
        Collections.sort(sortedNames);

        Assert.assertEquals(productNames.size(), sortedNames.size());
        for(int i=0;i<productNames.size();i++) {
            Assert.assertEquals(productNames.get(i), sortedNames.get(i));
        }
    }
}
