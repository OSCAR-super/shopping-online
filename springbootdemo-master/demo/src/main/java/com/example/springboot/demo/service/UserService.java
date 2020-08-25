package com.example.springboot.demo.service;


import com.example.springboot.demo.dao.UserMapper;
import com.example.springbootdemoentity.entity.Order;
import com.example.springbootdemoentity.entity.Product;
import com.example.springbootdemoentity.entity.image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class UserService implements Serializable {
    @Autowired
    private UserMapper userMapper;


    public void sI() {
        System.out.println("sss");
    }

    public List<Product> getTag(String tag) {
        return userMapper.getTag(tag);
    }

    public List<Product> getProduct(String productName) {
        return userMapper.getProduct(productName);
    }

    public Order getOrderId(String orderId) {
        return userMapper.getOrderId(orderId);
    }

    public void setOrder(String orderId, String orderTime,String Id,String face,String name,String time) {
        userMapper.setOrder(orderId,orderTime,Id,face,name,time);
    }

    public List<Order> getOrder(String id) {
        return userMapper.getOrder(id);
    }

    public void achieveOrderId(String orderId) {
        userMapper.achieveOrderId(orderId);
    }

    public void concelOrderId(String orderId) {
        userMapper.concelOrderId(orderId);
    }

    public List<image> findProductImage(String productId) {
        return userMapper.findProductImage(productId);
    }

    public void consume(String productId, String productSurplus, String productPrice) {
        userMapper.consume(productId,productSurplus,productPrice);
    }


    public Product getP(String productId) {
        return userMapper.getP(productId);
    }
}
