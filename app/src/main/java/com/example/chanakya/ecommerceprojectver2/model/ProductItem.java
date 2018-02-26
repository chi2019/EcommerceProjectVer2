package com.example.chanakya.ecommerceprojectver2.model;

/**
 * Created by chanakya on 2/25/18.
 */

public class ProductItem {

    String name;
    String image;
    String id;
    String description;
    String price;
    String quantity;

    public ProductItem(String name, String image, String id, String description, String price, String quantity) {
        this.name = name;
        this.image = image;
        this.id = id;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
