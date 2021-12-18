package com.example.mernmarketplace.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ShopCreationResponse implements Serializable
{

    @SerializedName("number_of_sales")
    @Expose
    private Integer numberOfSales;
    @SerializedName("is_a_weak_seller")
    @Expose
    private Boolean isAWeakSeller;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("owner")
    @Expose
    private Owner owner;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getImage() {
        return image1;
    }

    public void setImage(String image) {
        this.image1 = image;
    }


    private String image1;
    private final static long serialVersionUID = 4582819814565892300L;

    /**
     * No args constructor for use in serialization
     *
     */
    public ShopCreationResponse() {
    }

    /**
     *
     * @param owner
     * @param numberOfSales
     * @param created
     * @param v
     * @param name
     * @param description
     * @param id
     * @param isAWeakSeller
     */
    public ShopCreationResponse(Integer numberOfSales, Boolean isAWeakSeller, String id, String name, String description, String created, Owner owner, Integer v) {
        super();
        this.numberOfSales = numberOfSales;
        this.isAWeakSeller = isAWeakSeller;
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.owner = owner;
        this.v = v;
    }

    public ShopCreationResponse(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Integer getNumberOfSales() {
        return numberOfSales;
    }

    public void setNumberOfSales(Integer numberOfSales) {
        this.numberOfSales = numberOfSales;
    }

    public Boolean getIsAWeakSeller() {
        return isAWeakSeller;
    }

    public void setIsAWeakSeller(Boolean isAWeakSeller) {
        this.isAWeakSeller = isAWeakSeller;
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}