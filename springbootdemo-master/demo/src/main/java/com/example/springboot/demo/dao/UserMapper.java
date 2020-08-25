package com.example.springboot.demo.dao;



import com.example.springbootdemoentity.entity.Order;
import com.example.springbootdemoentity.entity.Product;
import com.example.springbootdemoentity.entity.image;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

    List<Product> getTag(@Param("productTag") String productTag);

    List<Product> getProduct(@Param("productName")String productName);

    Order getOrderId(@Param("orderId")String orderId);

    void setOrder(@Param("orderId")String orderId, @Param("orderTime")String orderTime,@Param("Id")String Id,@Param("face")String face,@Param("name")String name,@Param("time")String time);

    List<Order> getOrder(@Param("Id")String id);

    void achieveOrderId(@Param("orderId")String orderId);

    void concelOrderId(@Param("orderId")String orderId);
    List<image> findProductImage(@Param("productId")String productId);

    void consume(@Param("productId")String productId,@Param("productSurplus") String productSurplus,@Param("productPrice") String productPrice);

    Product getP(@Param("productId")String productId);
}
