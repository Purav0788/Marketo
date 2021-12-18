package com.example.mernmarketplace.models;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuctionCreationResponse implements Serializable
{

    @SerializedName("startingBid")
    @Expose
    private Integer startingBid;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("bidStart")
    @Expose
    private String bidStart;
    @SerializedName("bidEnd")
    @Expose
    private String bidEnd;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("bids")
    @Expose
    private List<Object> bids = null;
    @SerializedName("seller")
    @Expose
    private Seller seller;
    @SerializedName("__v")
    @Expose
    private Integer v;
    private final static long serialVersionUID = 5200817988908281732L;

    /**
     * No args constructor for use in serialization
     *
     */
    public AuctionCreationResponse() {
    }

    /**
     *
     * @param seller
     * @param itemName
     * @param created
     * @param v
     * @param startingBid
     * @param bidStart
     * @param bids
     * @param id
     * @param bidEnd
     */
    public AuctionCreationResponse(Integer startingBid, String id, String itemName, String bidStart, String bidEnd, String created, List<Object> bids, Seller seller, Integer v) {
        super();
        this.startingBid = startingBid;
        this.id = id;
        this.itemName = itemName;
        this.bidStart = bidStart;
        this.bidEnd = bidEnd;
        this.created = created;
        this.bids = bids;
        this.seller = seller;
        this.v = v;
    }

    public Integer getStartingBid() {
        return startingBid;
    }

    public void setStartingBid(Integer startingBid) {
        this.startingBid = startingBid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBidStart() {
        return bidStart;
    }

    public void setBidStart(String bidStart) {
        this.bidStart = bidStart;
    }

    public String getBidEnd() {
        return bidEnd;
    }

    public void setBidEnd(String bidEnd) {
        this.bidEnd = bidEnd;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public List<Object> getBids() {
        return bids;
    }

    public void setBids(List<Object> bids) {
        this.bids = bids;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}


