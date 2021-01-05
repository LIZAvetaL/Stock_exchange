package com.example.stock_exchange.repository;

import com.example.stock_exchange.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, Integer> {
}
