package com.example.springbootdemoconsumer.controller;


import com.example.springbootdemoconsumer.service.ProductService;
import com.example.springbootdemoentity.entity.Consumer;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {
    @Autowired
    private ProductService productService;




    @RequestMapping(value = "getConsumer")
    public String getConsumer(){
        String str =  productService.getProduct();




        return str;
    }
}
