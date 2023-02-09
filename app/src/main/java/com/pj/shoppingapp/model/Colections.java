package com.pj.shoppingapp.model;

public class Colections {

    String rating;
    String name;
    Integer price;
    Integer id;
    String urlImage;
    int bigImageurl;
    String description;
    String account;
    String unit;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }







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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Colections(String unit,String account,Integer id, String urlImage, String name , Integer price, String rating, String description) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.urlImage = urlImage;
        this.rating = rating;
        this.description= description;
        this.account= account;
        this.unit = unit;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public int getBigImageurl() {
        return bigImageurl;
    }

    public void setBigImageurl(int bigImageurl) {
        this.bigImageurl = bigImageurl;
    }



    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


}
