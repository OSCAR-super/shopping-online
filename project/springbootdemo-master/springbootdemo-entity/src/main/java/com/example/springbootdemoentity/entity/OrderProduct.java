package com.example.springbootdemoentity.entity;

public class OrderProduct {
    @Override
    public String toString() {
        return "OrderProduct{" +
                "product=" + product +
                ", number='" + number + '\'' +
                '}';
    }

    private Product product;
    private String number;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
