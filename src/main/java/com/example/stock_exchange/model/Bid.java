package com.example.stock_exchange.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "bids")
public class Bid {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "issuer")
    private String issuer;
    @Column(name = "bid_number")
    private int bidNumber;
    @Column(name = "amount")
    private int amount;
    @Column(name = "max_price")
    private double maxPrice;
    @Column(name = "min_price")
    private double minPrice;
    @Column(name = "priority")
    private int priority;
    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "due_date")
    private Date dueDate;
    @Column(name = "bid_status")
    private int bidStatus;

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public int getBidNumber() {
        return bidNumber;
    }

    public void setBidNumber(int bidNumber) {
        this.bidNumber = bidNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(int bidStatus) {
        this.bidStatus = bidStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bid bids = (Bid) o;

        if (bidNumber != bids.bidNumber) return false;
        if (amount != bids.amount) return false;
        if (id != bids.id) return false;
        if (Double.compare(bids.maxPrice, maxPrice) != 0) return false;
        if (priority != bids.priority) return false;
        if (Double.compare(bids.minPrice, minPrice) != 0) return false;
        if (bidStatus != bids.bidStatus) return false;
        if (issuer != null ? !issuer.equals(bids.issuer) : bids.issuer != null) return false;
        if (creationDate != null ? !creationDate.equals(bids.creationDate) : bids.creationDate != null) return false;
        if (dueDate != null ? !dueDate.equals(bids.dueDate) : bids.dueDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = issuer != null ? issuer.hashCode() : 0;
        result = 31 * result + bidNumber;
        result = 31 * result + amount;
        result = 31 * result + id;
        temp = Double.doubleToLongBits(maxPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + priority;
        temp = Double.doubleToLongBits(minPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + bidStatus;
        return result;
    }
}
