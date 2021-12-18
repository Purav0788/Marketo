package com.example.mernmarketplace.models;

public class Product {
    private String name;

    public Product(String name,int quantity, double price) {
        this.name = name;
        this.quantity =quantity;
        this.price = price;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    private String _id;
    private String image;
    private String description;
    private String category;
    private int quantity;
    private double price;
    private Shop shop;

    public Product(String name, String image, String description, String category, int quantity, double price, Shop shop,String _id) {
        this._id = _id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.shop = shop;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
