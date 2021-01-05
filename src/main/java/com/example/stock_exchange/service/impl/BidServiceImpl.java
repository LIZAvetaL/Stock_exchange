package com.example.stock_exchange.service.impl;

import com.example.stock_exchange.model.Bid;
import com.example.stock_exchange.repository.BidRepository;
import com.example.stock_exchange.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidServiceImpl implements BidService {
    private final BidRepository bidRepository;

    @Autowired
    public BidServiceImpl(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    @Override
    public List<Bid> findBrokersBids(int id) {
        return bidRepository.findBidsByBrokerId(id);
    }

    @Override
    public Bid getBid(int id) {
        return bidRepository.findById(id).get();
    }
}
