package com.example.springbootdemoconsumer.service;

import com.example.springbootdemoconsumer.Email.CodeUtil;
import com.example.springbootdemoconsumer.Email.SendEamilUtil;
import com.example.springbootdemoconsumer.dao.UserMapper;
import com.example.springbootdemoentity.entity.Product;
import com.example.springbootdemoentity.entity.User;
import com.example.springbootdemoentity.entity.image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUser(){
        return userMapper.getUser();
    }

    public void sendEamil(HttpServletRequest request, HttpServletResponse response, String UserEmail) {
        String toEmail=UserEmail;
        String code;
        SendEamilUtil sendEamilUtil=new SendEamilUtil();
        CodeUtil codeUtil=new CodeUtil();
        code=codeUtil.generateUniqueCode();

        try {
            sendEamilUtil.sendemail(request,response,toEmail,code);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }



    }

    public void addProduct(String productName, String productSurplus, String productDescription, String productPrice,String productTag,String productBrand,String productUnit,String ProductPlace,String ProductDdl,Boolean ProductChange,String ProductLevel,String ProductContent) {
    userMapper.addProduct(productName,productSurplus,productDescription,productPrice,productTag,productBrand,productUnit,ProductPlace,ProductDdl,ProductChange,ProductLevel,ProductContent);
    }

    public List<Product> findAllProduct() {
        return userMapper.findAllProduct();
    }

    public List<image> findProductImage(String productId) {
        return userMapper.findProductImage(productId);
    }

    public void addImage(String productId, String fileName,String imageState) {
        userMapper.addImage(productId,fileName,imageState);
    }

    public void soldoutProduct(String productId) {
        userMapper.soldoutProduct(productId);
    }

    public void IncreaseProduct(String productId, String productSurplus) {
    userMapper.IncreaseProduct(productId,productSurplus);
    }

    public Product findProductOne(String productName) {
        return userMapper.findProductOne(productName);
    }


    public void changePriceProduct(String productId, String productPrice) {
    userMapper.changePriceProduct(productId,productPrice);
    }


    public List<String> t() {
        return userMapper.t();
    }
}
