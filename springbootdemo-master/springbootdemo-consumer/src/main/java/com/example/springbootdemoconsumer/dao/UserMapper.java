package com.example.springbootdemoconsumer.dao;


import com.example.springbootdemoentity.entity.Product;
import com.example.springbootdemoentity.entity.User;
import com.example.springbootdemoentity.entity.image;
import feign.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {
     User getUser();

    void addProduct(@Param("productName") String productName,@Param("productSurplus") String productSurplus, @Param("productDescription")String productDescription,@Param("productPrice") String productPrice,@Param("productTag")String productTag,@Param("productBrand")String productBrand,@Param("productUnit")String productUnit,@Param("productPlace")String productPlace,@Param("productDdl")String productDdl,@Param("productChange")Boolean productChange,@Param("productLevel")String productLevel,@Param("productContent")String productContent);

    List<Product> findAllProduct();

    List<image> findProductImage(@Param("productId")String productId);

    void addImage(@Param("productId")String productId,@Param("imageName") String imageName,@Param("imageState")String imageState);

    void soldoutProduct(@Param("productId")String productId);

    void IncreaseProduct(@Param("productId")String productId,@Param("productSurplus") String productSurplus);

    Product findProductOne(@Param("productName")String productName);

    void changePriceProduct(@Param("productId")String productId, @Param("productPrice")String productPrice);

    List<String> t();
}
