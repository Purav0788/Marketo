package com.example.mernmarketplace.models;

import java.util.List;

public class Order {
    private List<Product> products;
    private String customer_name;
    private String customer_email;
  private DeliveryAddress delivery_address;

    public Order(List<Product> products, String customer_name, String customer_email, DeliveryAddress delivery_address) {
        this.products = products;
        this.customer_name = customer_name;
        this.customer_email = customer_email;
        this.delivery_address = delivery_address;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public DeliveryAddress getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(DeliveryAddress delivery_address) {
        this.delivery_address = delivery_address;
    }
}
