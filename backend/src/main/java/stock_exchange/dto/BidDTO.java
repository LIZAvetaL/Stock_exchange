package stock_exchange.dto;

import java.sql.Date;

public class BidDTO {
    private int id;
    private String issuer;

    private int bidNumber;
    private int amount;

    private double maxPrice;
    private double minPrice;
    private String priority;

    private String creationDate;
    private String dueDate;

    private String status;
    private String broker;
    private String client;

    public BidDTO() {}
    public BidDTO(String issuer, int bidNumber, int amount, double maxPrice,
                  double minPrice, String priority, String creationDate, String dueDate,
                  String status, String broker, String client) {
        this.issuer = issuer;
        this.bidNumber = bidNumber;
        this.amount = amount;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.priority = priority;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.status = status;
        this.broker = broker;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
