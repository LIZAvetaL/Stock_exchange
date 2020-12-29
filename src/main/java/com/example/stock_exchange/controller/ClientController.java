package com.example.stock_exchange.controller;

import com.example.stock_exchange.dto.BrokerDTO;
import com.example.stock_exchange.dto.UserDTO;
import com.example.stock_exchange.service.BrokerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final BrokerService brokerService;

    public ClientController(BrokerService brokerService) {
        this.brokerService = brokerService;
    }

    @GetMapping
    public List<BrokerDTO> findAll() {
        return brokerService.findAll();
    }

    @PostMapping("/employ-broker/{client-id}/{broker-id}")
    public BrokerDTO employBroker(@PathVariable(name = "client-id") int userId,@PathVariable(name = "broker-id") int brokerId){
        return brokerService.employBroker(userId,brokerId);
    }
}
