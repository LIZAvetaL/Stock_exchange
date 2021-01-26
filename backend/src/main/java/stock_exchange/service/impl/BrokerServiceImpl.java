package stock_exchange.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import stock_exchange.dto.BidDTO;
import stock_exchange.dto.BrokerDTO;
import stock_exchange.model.Bid;
import stock_exchange.model.Broker;
import stock_exchange.model.Deal;
import stock_exchange.model.Status;
import stock_exchange.model.User;
import stock_exchange.repository.BrokerRepository;
import stock_exchange.service.BidService;
import stock_exchange.service.BrokerService;
import stock_exchange.service.DealService;
import stock_exchange.service.StatusService;
import stock_exchange.service.UserService;
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
    private final StatusService statusService;

    @Autowired
    public BrokerServiceImpl(BrokerRepository brokerRepository, UserService userService,
                             BidService bidService, DealService dealService, StatusService statusService) {
        this.brokerRepository = brokerRepository;
        this.userService = userService;
        this.bidService = bidService;
        this.dealService = dealService;
        this.statusService = statusService;
    }

    @Override
    public List<BrokerDTO> findAll() {
        List<Broker> brokers = brokerRepository.findAll();
        List<BrokerDTO> brokerDTOs = new ArrayList<>();
        for (Broker broker : brokers) {
            brokerDTOs.add(transfer(broker));
        }
        return brokerDTOs;
    }

    @Override
    public BrokerDTO employBroker(int clientId, int brokerId) {
        User client = userService.findUser(clientId);
        Broker broker = brokerRepository.findById(brokerId).get();
        broker.setEmployer(client);
        brokerRepository.save(broker);
        return transfer(broker);
    }

    @Override
    public Deal createDeal(int sellerBidId, int buyerBidId) {
        Bid sellerBid = bidService.getBid(sellerBidId);
        Bid buyerBid = bidService.getBid(buyerBidId);
        Deal deal = new Deal();
        dealService.save(deal);
        return deal;
    }

    @Override
    public Page<BrokerDTO> findAllUnemployed(String title, int page, int size, String sort) {
        Pageable pagingSort = PageRequest.of(page, size, Sort.by(sort));

        Status status = statusService.find("unemployed");
        Page<Broker> tempPage = brokerRepository.findAllByStatus(status, pagingSort);
        return tempPage.map(BrokerServiceImpl::transfer);
    }

    @Override
    public BrokerDTO findBroker(int id) {

        return transfer(brokerRepository.findById(id).get());
    }

    @Override
    public List<BrokerDTO> findBrokers(int clientId) {
        List<Broker> brokers=brokerRepository.findBrokersByEmployerId(clientId);
        List<BrokerDTO> brokerDTOs= new ArrayList<>();
        for (Broker broker:brokers){
            brokerDTOs.add(transfer(broker));
        }
        return brokerDTOs;
    }

    private static BrokerDTO transfer(Broker broker) {
        return new BrokerDTO(broker.getId(), broker.getBroker().getName(),
                broker.getEmployer().getName(), broker.getStatus().getStatusName(),
                broker.getExchange().getExchangeName());
    }
}
