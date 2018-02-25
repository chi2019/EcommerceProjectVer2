package com.example.chanakya.ecommerceprojectver2.model;

/**
 * Created by chanakya on 2/23/18.
 */

public class Item {

    String name;
    String image;
    String id;
    String description;

    public Item(String categoryName, String categoryImage,String categoryId,String description) {
        name = categoryName;
        image = categoryImage;
        id = categoryId;
        this.description = description;
    }

    public String getCategoryName() {
        return name;
    }

    public void setCategoryName(String categoryName) {
        name = categoryName;
    }

    public String getCategoryImage() {
        return image;
    }

    public void setCategoryImage(String categoryImage) {
        image = categoryImage;
    }

    public String getCategoryId() {
        return id;
    }

    public void setCategoryId(String categoryId) {
        id = categoryId;
    }
}
