package com.example.stock_exchange.service.impl;

import com.example.stock_exchange.dto.BrokerDTO;
import com.example.stock_exchange.model.Broker;
import com.example.stock_exchange.model.User;
import com.example.stock_exchange.repository.BrokerRepository;
import com.example.stock_exchange.repository.UserRepository;
import com.example.stock_exchange.service.BrokerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BrokerServiceImpl implements BrokerService {

    @Autowired
    BrokerRepository brokerRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<BrokerDTO> findAll() {
        List<Broker> brokers = brokerRepository.findAll();
        List<BrokerDTO> brokerDTOs = new ArrayList<>();
        for (Broker broker : brokers) {
            BrokerDTO brokerDTO = new BrokerDTO(broker.getId(), broker.getExchange().getExchangeName(),
                    broker.getEmployer().getName(), broker.getStatus().getStatusName());
            brokerDTOs.add(brokerDTO);
        }
        return brokerDTOs;
    }

    @Override
    public BrokerDTO employBroker(int clientId, int brokerId) {
        User client = userRepository.getOne(clientId);
        Broker broker = brokerRepository.getOne(brokerId);
        broker.setEmployer(client);
        brokerRepository.save(broker);
        return new BrokerDTO(broker.getId(), broker.getExchange().getExchangeName(),
                broker.getEmployer().getName(), broker.getStatus().getStatusName());
    }
}
