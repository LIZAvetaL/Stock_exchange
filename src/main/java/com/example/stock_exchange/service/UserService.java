package com.example.stock_exchange.service;

import com.example.stock_exchange.dto.UserDTO;
import com.example.stock_exchange.model.User;

import java.util.List;

public interface UserService {

    User findById(int id);
    List<UserDTO> findAll();
    void save(UserDTO user);
}
