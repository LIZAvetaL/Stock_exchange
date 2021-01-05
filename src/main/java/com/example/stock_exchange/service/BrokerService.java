package com.example.stock_exchange.service;

import com.example.stock_exchange.dto.BidDTO;
import com.example.stock_exchange.dto.BrokerDTO;
import com.example.stock_exchange.dto.DealDTO;
import com.example.stock_exchange.model.Deal;

import java.util.List;

public interface BrokerService {
    List<BrokerDTO> findAll();

    BrokerDTO employBroker(int clientId, int brokerId);

    List<BidDTO> findBrokersBids(int id);
    Deal createDeal(int sellerBidId, int buyerBidId);
}
