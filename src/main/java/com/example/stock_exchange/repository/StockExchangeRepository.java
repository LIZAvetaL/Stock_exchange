package com.example.stock_exchange.repository;

import com.example.stock_exchange.model.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockExchangeRepository extends JpaRepository<StockExchange, Integer> {
    List<StockExchange> findStockExchangeByOwner(int ownerId);
}
