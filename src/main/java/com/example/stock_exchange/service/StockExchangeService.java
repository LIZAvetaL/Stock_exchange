package com.example.stock_exchange.service;

import com.example.stock_exchange.dto.StockExchangeDTO;

import java.util.List;

public interface StockExchangeService {
    List<StockExchangeDTO> findByOwner(int owner);
}
