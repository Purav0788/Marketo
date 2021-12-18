package com.example.mernmarketplace.models;


import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class OrderResponse implements Serializable
{

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("products")
    @Expose
    private List<Product> products = null;
    @SerializedName("customer_name")
    @Expose
    private String customerName;
    @SerializedName("customer_email")
    @Expose
    private String customerEmail;
    @SerializedName("delivery_address")
    @Expose
    private DeliveryAddress deliveryAddress;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("__v")
    @Expose
    private Integer v;
    private final static long serialVersionUID = -3301627451118660286L;

    /**
     * No args constructor for use in serialization
     *
     */
    public OrderResponse() {
    }

    /**
     *
     * @param deliveryAddress
     * @param created
     * @param v
     * @param customerEmail
     * @param id
     * @param user
     * @param customerName
     * @param products
     */
    public OrderResponse(String id, List<Product> products, String customerName, String customerEmail, DeliveryAddress deliveryAddress, User user, String created, Integer v) {
        super();
        this.id = id;
        this.products = products;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.deliveryAddress = deliveryAddress;
        this.user = user;
        this.created = created;
        this.v = v;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

}
