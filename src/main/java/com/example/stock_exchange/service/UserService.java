package com.example.stock_exchange.service;

import com.example.stock_exchange.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO findById(int id);
    List<UserDTO> findAll();
    void save(UserDTO user);
}
