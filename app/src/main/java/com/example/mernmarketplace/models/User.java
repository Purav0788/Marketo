package com.example.mernmarketplace.models;

import com.google.gson.annotations.SerializedName;

public class User {

    private String name;
    private String email;
    private String hashed_password;
    private String _id;
    private boolean seller;

    public User(String name, String email, String hashed_password,boolean seller) {
        this.name = name;
        this.email = email;
        this.hashed_password = hashed_password;
        this.seller = seller;
    }

    public User(String name, String email, boolean seller) {
        this.name = name;
        this.email = email;
        this.seller = seller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashed_password() {
        return hashed_password;
    }

    public void setHashed_password(String hashed_password) {
        this.hashed_password = hashed_password;
    }

    public boolean isSeller() {
        return seller;
    }

    public void setSeller(boolean seller) {
        this.seller = seller;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
