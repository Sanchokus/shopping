package ru.aolisov.shoppingserver.mvc;

import org.hibernate.JDBCException;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.aolisov.shoppingserver.data.entity.Product;
import ru.aolisov.shoppingserver.data.service.ProductService;
import ru.aolisov.shoppingserver.message.MessageObject;

import java.util.List;
import java.util.Map;


/**
 * Created by Alex on 2/18/2016.
 */

@Controller
@Scope("session")
@RequestMapping("/")
public class MainController {

    @Autowired
    ProductService productService;

    @RequestMapping("/")
    String openMainPage() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    String openLoginPage() {
        return "login";
    }

    @RequestMapping("/404")
    String open404() {
        return "404";
    }

    @RequestMapping(value = {"/product", "/product/list"}, method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<Object> getAllProducts() {
        try {
            return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
        } catch (GenericJDBCException e) {
            e.printStackTrace();
            return generateErrorResponse(e.getSQLException().getLocalizedMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageObject(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        try {
            productService.save(product);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (JDBCException e) {
            e.printStackTrace();
            return generateErrorResponse(e.getSQLException().getLocalizedMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageObject(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Object> updateProduct(@RequestBody Product product) {
        try {
            productService.update(product);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (JDBCException e) {
            e.printStackTrace();
            return generateErrorResponse(e.getSQLException().getLocalizedMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageObject(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/product", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Object> deleteProduct(@RequestBody Product product) {
        try {
            productService.delete(product);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (JDBCException e) {
            e.printStackTrace();
            return generateErrorResponse(e.getSQLException().getLocalizedMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageObject(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<Object> generateErrorResponse(String message) {
        return new ResponseEntity<Object>(new MessageObject(message), HttpStatus.BAD_REQUEST);
    }
}
