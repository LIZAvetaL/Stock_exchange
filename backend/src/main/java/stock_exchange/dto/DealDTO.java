package stock_exchange.dto;

import java.sql.Date;

public class DealDTO {
    private int id;
    private int amount;
    private Double price;
    private Double totalPrice;
    private Date bargainDate;
    private String seller;
    private String buyer;

    public DealDTO(int id, int amount, Double price,
                   Double totalPrice, Date bargainDate, String seller, String buyer) {
        this.id = id;
        this.amount = amount;
        this.price = price;
        this.totalPrice = totalPrice;
        this.bargainDate = bargainDate;
        this.seller = seller;
        this.buyer = buyer;
    }

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

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }
}
