package com.example.mernmarketplace.models;

import java.util.Date;
import java.util.List;

public class Auction {
    private String itemName;
    private String description;
    private String image;
    private Date bidStart;
    private Date bidEnd;
    private List<Bid> bids=  null;

    public Auction(String itemName, String description, String image, Date bidStart, Date bidEnd, List<Bid> bids, User seller) {
        this.itemName = itemName;
        this.description = description;
        this.image = image;
        this.bidStart = bidStart;
        this.bidEnd = bidEnd;
        this.bids = bids;
        this.seller = seller;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    private User seller;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getBidStart() {
        return bidStart;
    }

    public void setBidStart(Date bidStart) {
        this.bidStart = bidStart;
    }

    public Date getBidEnd() {
        return bidEnd;
    }

    public void setBidEnd(Date bidEnd) {
        this.bidEnd = bidEnd;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
}
