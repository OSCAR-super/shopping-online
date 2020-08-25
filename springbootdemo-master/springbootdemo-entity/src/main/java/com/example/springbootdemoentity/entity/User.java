package com.example.springbootdemoentity.entity;


import lombok.Data;

@Data
public class User {
    private int sellerId;
    private String sellerName;

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getSellerLoginName() {
        return sellerLoginName;
    }

    public void setSellerLoginName(String sellerLoginName) {
        this.sellerLoginName = sellerLoginName;
    }

    public String getSellerPassword() {
        return sellerPassword;
    }

    public void setSellerPassword(String sellerPassword) {
        this.sellerPassword = sellerPassword;
    }

    public String getSellerStore() {
        return sellerStore;
    }

    public void setSellerStore(String sellerStore) {
        this.sellerStore = sellerStore;
    }

    public String getSellerImage() {
        return sellerImage;
    }

    public void setSellerImage(String sellerImage) {
        this.sellerImage = sellerImage;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    private String sellerAddress;
    private String sellerLoginName;
    private String sellerPassword;
    private String sellerStore;
    private String sellerImage;

    @Override
    public String toString() {
        return "User{" +
                "sellerId=" + sellerId +
                ", sellerName='" + sellerName + '\'' +
                ", sellerAddress='" + sellerAddress + '\'' +
                ", sellerLoginName='" + sellerLoginName + '\'' +
                ", sellerPassword='" + sellerPassword + '\'' +
                ", sellerStore='" + sellerStore + '\'' +
                ", sellerImage='" + sellerImage + '\'' +
                ", sellerPhone='" + sellerPhone + '\'' +
                '}';
    }

    private String sellerPhone;

}
