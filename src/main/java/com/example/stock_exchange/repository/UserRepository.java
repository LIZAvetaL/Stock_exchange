package com.example.stock_exchange.repository;

import com.example.stock_exchange.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
