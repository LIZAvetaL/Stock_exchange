package stock_exchange.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import stock_exchange.config.StatusConst;
import stock_exchange.dto.BidDTO;
import stock_exchange.dto.BrokerDTO;
import stock_exchange.dto.UnemployedBrokerDTO;
import stock_exchange.exception.NotFoundException;
import stock_exchange.model.Bid;
import stock_exchange.model.Broker;
import stock_exchange.model.Deal;
import stock_exchange.model.Status;
import stock_exchange.model.User;
import stock_exchange.repository.BrokerRepository;
import stock_exchange.response.MessageResponse;
import stock_exchange.service.BidService;
import stock_exchange.service.BrokerService;
import stock_exchange.service.DealService;
import stock_exchange.service.StatusService;
import stock_exchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrokerServiceImpl implements BrokerService {

    private final BrokerRepository brokerRepository;
    private final UserService userService;

    private final DealService dealService;
    private final StatusService statusService;

    @Autowired
    public BrokerServiceImpl(BrokerRepository brokerRepository, UserService userService,
                             DealService dealService, StatusService statusService) {
        this.brokerRepository = brokerRepository;
        this.userService = userService;
        this.dealService = dealService;
        this.statusService = statusService;
    }

    @Override
    public List<BrokerDTO> findAll() {
        List<Broker> brokers = brokerRepository.findAll();
        List<BrokerDTO> brokerDTOs = new ArrayList<>();
        for (Broker broker : brokers) {
            brokerDTOs.add(transfertoDto(broker));
        }
        return brokerDTOs;
    }

    @Override
    public Deal createDeal(int sellerBidId, int buyerBidId) {
        //Bid sellerBid = bidService.getBid(sellerBidId);
        // Bid buyerBid = bidService.getBid(buyerBidId);
        Deal deal = new Deal();
        dealService.save(deal);
        return deal;
    }

    @Override
    public Page<UnemployedBrokerDTO> findAllUnemployed(String title, int page, int size, String sort) {
        Pageable pagingSort = PageRequest.of(page, size, Sort.by(sort));

        Status status = statusService.find(StatusConst.Unemployed.toString());
        Page<Broker> tempPage = brokerRepository.findAllByStatus(status, pagingSort);
        return tempPage.map(this::transfer);
    }

    @Override
    public BrokerDTO findBroker(int id) {

        return transfertoDto(brokerRepository.findById(id).get());
    }

    @Override
    public Broker findBroker(String name) {

        return brokerRepository.findBrokerByBrokerName(name);
    }

    @Override
    public Page<BrokerDTO> findBrokers(int page, int size, int clientId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Broker> brokers = brokerRepository.findBrokersByEmployerId(clientId, pageable);
        return brokers.map(this::transfertoDto);
    }

    @Override
    public List<BrokerDTO> findBrokers(int clientId) {
        return brokerRepository.findBrokersByEmployerId(clientId)
                .stream()
                .map(this::transfertoDto)
                .collect(Collectors.toList());
    }

    @Override
    public MessageResponse employ(int brokerId, int clientId) {
        Broker broker = brokerRepository.findById(brokerId).orElseThrow(
                () -> new NotFoundException("User error")
        );
        broker.setEmployer(userService.findUser(clientId));
        broker.setStatus(statusService.find(StatusConst.Employed.toString()));
        brokerRepository.save(broker);
        return new MessageResponse("ok");
    }

    @Override
    public MessageResponse dismiss(int brokerId) {
        Broker broker = brokerRepository.findById(brokerId).orElseThrow(
                () -> new NotFoundException("User error")
        );
        broker.setEmployer(null);
        broker.setStatus(statusService.find(StatusConst.Unemployed.toString()));
        brokerRepository.save(broker);
        return new MessageResponse("ok");
    }

    private UnemployedBrokerDTO transfer(Broker broker) {
        return new UnemployedBrokerDTO(broker.getId(), broker.getBroker().getName(),
                broker.getStatus().getStatusName(),
                broker.getExchange().getExchangeName());
    }

    private BrokerDTO transfertoDto(Broker broker) {
        return new BrokerDTO(broker.getId(), broker.getBroker().getName(),
                broker.getEmployer().getName(),
                broker.getStatus().getStatusName(),
                broker.getExchange().getExchangeName());
    }
}
