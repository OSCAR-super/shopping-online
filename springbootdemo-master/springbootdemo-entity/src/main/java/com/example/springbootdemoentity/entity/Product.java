package com.example.springbootdemoentity.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Product {
    private int productId;
    private String productName;

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productSurplus='" + productSurplus + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", productTag='" + productTag + '\'' +
                ", productBrand='" + productBrand + '\'' +
                ", productUnit='" + productUnit + '\'' +
                ", productState='" + productState + '\'' +
                ", productPlace='" + productPlace + '\'' +
                ", productDdl='" + productDdl + '\'' +
                ", productChange=" + productChange +
                ", productLevel='" + productLevel + '\'' +
                ", productContent='" + productContent + '\'' +
                '}';
    }

    private String productSurplus;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSurplus() {
        return productSurplus;
    }

    public void setProductSurplus(String productSurplus) {
        this.productSurplus = productSurplus;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductTag() {
        return productTag;
    }

    public void setProductTag(String productTag) {
        this.productTag = productTag;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getProductState() {
        return productState;
    }

    public void setProductState(String productState) {
        this.productState = productState;
    }

    public String getProductPlace() {
        return productPlace;
    }

    public void setProductPlace(String productPlace) {
        this.productPlace = productPlace;
    }

    public String getProductDdl() {
        return productDdl;
    }

    public void setProductDdl(String productDdl) {
        this.productDdl = productDdl;
    }

    public Boolean getProductChange() {
        return productChange;
    }

    public void setProductChange(Boolean productChange) {
        this.productChange = productChange;
    }

    public String getProductLevel() {
        return productLevel;
    }

    public void setProductLevel(String productLevel) {
        this.productLevel = productLevel;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }

    private String productDescription;
    private String productPrice;

    private String productTag;
    private String productBrand;
    private String productUnit;
    private String productState;
    private String productPlace;
    private String productDdl;
    private Boolean productChange;
    private String productLevel;
    private String productContent;
}
