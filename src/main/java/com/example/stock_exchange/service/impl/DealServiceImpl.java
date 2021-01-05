package com.example.stock_exchange.service.impl;

import com.example.stock_exchange.model.Deal;
import com.example.stock_exchange.repository.DealRepository;
import com.example.stock_exchange.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;

    @Autowired
    public DealServiceImpl(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    @Override
    public Deal getDeal(int id) {
        return dealRepository.findById(id).get();
    }

    @Override
    public void save(Deal deal) {
        dealRepository.save(deal);
    }
}
