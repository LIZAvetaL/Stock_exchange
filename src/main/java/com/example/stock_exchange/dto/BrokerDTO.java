package com.example.stock_exchange.dto;

public class BrokerDTO {
    private int id;
    private String employer;
    private String status;
    private String exchange;

    public BrokerDTO(int id, String exchange, String employer, String status) {
        this.id = id;
        this.employer = employer;
        this.status = status;
        this.exchange = exchange;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}
