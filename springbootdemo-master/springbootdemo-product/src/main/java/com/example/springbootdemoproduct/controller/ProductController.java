package com.example.springbootdemoproduct.controller;


import com.example.springbootdemoentity.entity.Product;
import com.example.springbootdemoproduct.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @RequestMapping(value = "getProduct")
    public String getProduct() {
     Product product = new Product();
        return product.toString();
    }

    @RequestMapping(value = "/home")
    public String home(){
        System.out.println("123111");
        throw new MyException("101","错误");
    }
}
