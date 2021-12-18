package com.example.mernmarketplace.models;

import android.graphics.Bitmap;
import android.media.Image;

import java.sql.Blob;

public class Shop {
    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    private Byte[] image;
    private String _id;
    private String name;


    private String description;
    private int number_of_sales;
    private boolean is_a_weak_seller;
    private User owner;

    public Shop(Byte[] image,String name , String description, int number_of_sales, boolean is_a_weak_seller,User owner, String _id) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.number_of_sales = number_of_sales;
        this.is_a_weak_seller = is_a_weak_seller;
        this.owner = owner;
        this._id = _id;
    }

    public Shop(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumber_of_sales() {
        return number_of_sales;
    }

    public void setNumber_of_sales(int number_of_sales) {
        this.number_of_sales = number_of_sales;
    }

    public boolean isIs_a_weak_seller() {
        return is_a_weak_seller;
    }

    public void setIs_a_weak_seller(boolean is_a_weak_seller) {
        this.is_a_weak_seller = is_a_weak_seller;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
