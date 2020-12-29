package com.example.stock_exchange.repository;

import com.example.stock_exchange.model.Broker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrokerRepository extends JpaRepository<Broker, Integer> {
}
