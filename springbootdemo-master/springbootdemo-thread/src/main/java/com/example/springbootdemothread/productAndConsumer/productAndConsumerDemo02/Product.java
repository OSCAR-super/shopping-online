package com.example.springbootdemothread.productAndConsumer.productAndConsumerDemo02;



public class Product {
    /**
     * 定义一个产品，产品有产品名称、产品地址信息；
     */
    private String name;
    private String add;

    public Product(String name, String add) {
        this.name = name;
        this.add = add;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' + ", add='" + add + '\'';
    }
}
