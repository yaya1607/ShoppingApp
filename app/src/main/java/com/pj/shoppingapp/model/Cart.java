package com.pj.shoppingapp.model;

public class Cart {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSize() {return size;}

    public void setSize(String size) {this.size = size;}

    public String getAccount() {return account;}

    public void setAccount(String account) {this.account = account;}

    public Cart() {}

    public Cart(String unit,String account, String name, Integer price, String imageUrl, String size) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.size = size;
        this.account =account;
        this.unit = unit;
    }

    String size;
    String name;
    Integer price;
    String imageUrl;
    String account;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    String unit;
}
