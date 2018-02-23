package com.example.chanakya.ecommerceprojectver2.model;

/**
 * Created by chanakya on 2/23/18.
 */

public class Item {

    String CategoryName;
    String CategoryImage;

    public Item(String categoryName, String categoryImage) {
        CategoryName = categoryName;
        CategoryImage = categoryImage;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getCategoryImage() {
        return CategoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        CategoryImage = categoryImage;
    }
}
