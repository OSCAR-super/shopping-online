package com.example.springbootdemoentity.entity;

public class image {
    private int productImage;
    private String imageName;
    private String imageState;

    @Override
    public String toString() {
        return "image{" +
                "productImage=" + productImage +
                ", imageName='" + imageName + '\'' +
                ", imageState='" + imageState + '\'' +
                '}';
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageState() {
        return imageState;
    }

    public void setImageState(String imageState) {
        this.imageState = imageState;
    }
}
