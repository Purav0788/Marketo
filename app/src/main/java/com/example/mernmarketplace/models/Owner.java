package com.example.mernmarketplace.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Owner implements Serializable
{

    @SerializedName("seller")
    @Expose
    private Boolean seller;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("salt")
    @Expose
    private String salt;
    @SerializedName("hashed_password")
    @Expose
    private String hashedPassword;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("updated")
    @Expose
    private String updated;
    private final static long serialVersionUID = 3965235985523450348L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Owner() {
    }

    /**
     *
     * @param seller
     * @param salt
     * @param hashedPassword
     * @param created
     * @param v
     * @param name
     * @param id
     * @param updated
     * @param email
     */
    public Owner(Boolean seller, String id, String name, String email, String salt, String hashedPassword, String created, Integer v, String updated) {
        super();
        this.seller = seller;
        this.id = id;
        this.name = name;
        this.email = email;
        this.salt = salt;
        this.hashedPassword = hashedPassword;
        this.created = created;
        this.v = v;
        this.updated = updated;
    }

    public Boolean getSeller() {
        return seller;
    }

    public void setSeller(Boolean seller) {
        this.seller = seller;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

}