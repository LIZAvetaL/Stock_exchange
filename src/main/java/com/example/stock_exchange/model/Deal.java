package com.example.stock_exchange.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "deals")
public class Deal {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "amount")
    private int amount;
    @Column(name = "price")
    private Double price;
    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "bargain_date")
    private Date bargainDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getBargainDate() {
        return bargainDate;
    }

    public void setBargainDate(Date bargainDate) {
        this.bargainDate = bargainDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Deal deals = (Deal) o;

        if (id != deals.id) return false;
        if (amount != deals.amount) return false;
        if (price != null ? !price.equals(deals.price) : deals.price != null) return false;
        if (totalPrice != null ? !totalPrice.equals(deals.totalPrice) : deals.totalPrice != null) return false;
        if (bargainDate != null ? !bargainDate.equals(deals.bargainDate) : deals.bargainDate != null) return false;

        return true;
    }

}
