package com.example.mernmarketplace.models;

public class DeliveryAddress {
    private String street;
    private String city;
    private String state;
    private String zipcode;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private String country;

    public DeliveryAddress(String street, String city, String state, String zipcode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = "Pakistan";
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
