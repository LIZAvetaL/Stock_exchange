package com.example.stock_exchange.service;

import com.example.stock_exchange.model.Deal;

public interface DealService {
    Deal getDeal(int buyerBidId);

    void save(Deal deal);
}
