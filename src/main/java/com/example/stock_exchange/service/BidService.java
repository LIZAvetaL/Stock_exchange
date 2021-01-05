package com.example.stock_exchange.service;

import com.example.stock_exchange.model.Bid;

import java.util.List;

public interface BidService {
    List<Bid> findBrokersBids(int id);

    Bid getBid(int sellerBidId);
}
