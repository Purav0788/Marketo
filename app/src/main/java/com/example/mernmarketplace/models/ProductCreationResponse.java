package com.example.mernmarketplace.models;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ProductCreationResponse implements Serializable
{

    @SerializedName("_id")
    @Expose
    private String id;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("shop")
    @Expose
    private ShopCreationResponse shop;
    private final static long serialVersionUID = -5081085010842753126L;

    /**
     * No args constructor for use in serialization
     *
     */
    public ProductCreationResponse() {
    }

    /**
     *
     * @param quantity
     * @param shop
     * @param price
     * @param created
     * @param name
     * @param description
     * @param id
     * @param category
     */
    public ProductCreationResponse(String id, String name, String description, Integer quantity, Integer price, String category, String created, ShopCreationResponse shop) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.created = created;
        this.shop = shop;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public ShopCreationResponse getShop() {
        return shop;
    }

    public void setShop(ShopCreationResponse shop) {
        this.shop = shop;
    }

}