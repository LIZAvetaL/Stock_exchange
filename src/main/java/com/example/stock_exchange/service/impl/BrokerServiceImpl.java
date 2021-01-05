package com.example.stock_exchange.service.impl;

import com.example.stock_exchange.dto.BidDTO;
import com.example.stock_exchange.dto.BrokerDTO;
import com.example.stock_exchange.dto.DealDTO;
import com.example.stock_exchange.model.Bid;
import com.example.stock_exchange.model.Broker;
import com.example.stock_exchange.model.Deal;
import com.example.stock_exchange.model.User;
import com.example.stock_exchange.repository.BrokerRepository;
import com.example.stock_exchange.repository.UserRepository;
import com.example.stock_exchange.service.BidService;
import com.example.stock_exchange.service.BrokerService;
import com.example.stock_exchange.service.DealService;
import com.example.stock_exchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrokerServiceImpl implements BrokerService {

    private final BrokerRepository brokerRepository;
    private final UserService userService;
    private final BidService bidService;
    private final DealService dealService;

    @Autowired
    public BrokerServiceImpl(BrokerRepository brokerRepository, UserService userService, BidService bidService, DealService dealService) {
        this.brokerRepository = brokerRepository;
        this.userService = userService;
        this.bidService = bidService;
        this.dealService = dealService;
    }

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
        User client = userService.findById(clientId);
        Broker broker = brokerRepository.findById(brokerId).get();
        broker.setEmployer(client);
        brokerRepository.save(broker);
        return new BrokerDTO(broker.getId(), broker.getExchange().getExchangeName(),
                broker.getEmployer().getName(), broker.getStatus().getStatusName());
    }

    @Override
    public List<BidDTO> findBrokersBids(int id) {
        List<Bid> bids = bidService.findBrokersBids(id);
        List<BidDTO> bidDTOs = new ArrayList<>();
        for (Bid bid : bids) {
            BidDTO bidDTO = new BidDTO();
            bidDTO.setId(bid.getId());
            bidDTO.setAmount(bid.getAmount());
            bidDTO.setIssuer(bid.getIssuer());
            bidDTO.setBidNumber(bid.getBidNumber());
            bidDTO.setMaxPrice(bid.getMaxPrice());
            bidDTO.setMinPrice(bid.getMinPrice());
            bidDTO.setPriority(bidDTO.getPriority());
            bidDTO.setCreationDate(bid.getCreationDate());
            bidDTO.setDueDate(bid.getDueDate());
            bidDTO.setStatus(bid.getStatus().getStatusName());
            bidDTO.setBroker(bid.getClient().getName());
            bidDTOs.add(bidDTO);
        }
        return bidDTOs;
    }

    @Override
    public Deal createDeal(int sellerBidId, int buyerBidId) {
        Bid sellerBid= bidService.getBid(sellerBidId);
        Bid buyerBid=bidService.getBid(buyerBidId);
       Deal deal=new Deal(buyerBid.getAmount(), buyerBid.getMaxPrice(),sellerBid.getBroker(),buyerBid.getBroker());
       dealService.save(deal);
        return deal;
    }
}
