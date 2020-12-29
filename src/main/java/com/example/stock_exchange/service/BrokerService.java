package com.example.stock_exchange.service;

import com.example.stock_exchange.dto.BrokerDTO;

import java.util.List;

public interface BrokerService {
    List<BrokerDTO> findAll();

    BrokerDTO employBroker(int clientId, int brokerId);
}
