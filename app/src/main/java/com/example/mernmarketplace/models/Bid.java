package com.example.mernmarketplace.models;

import java.util.Date;

public class Bid {
    private User bidder;
    private double bid;
    private Date time;

    public Bid(User bidder, double bid, Date time) {
        this.bidder = bidder;
        this.bid = bid;
        this.time = time;
    }

    public User getBidder() {
        return bidder;
    }

    public void setBidder(User bidder) {
        this.bidder = bidder;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
