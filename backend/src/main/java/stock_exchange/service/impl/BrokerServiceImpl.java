package stock_exchange.service.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import stock_exchange.config.StatusConst;
import stock_exchange.dto.BidDTO;
import stock_exchange.dto.BrokerDTO;
import stock_exchange.dto.BrokerStatisticsDTO;
import stock_exchange.dto.StockExchangeDTO;
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
import stock_exchange.service.StockExchangeService;
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

    private final BidService bidService;
    private final StatusService statusService;
    private final StockExchangeService stockExchangeService;

    @Autowired
    public BrokerServiceImpl(BrokerRepository brokerRepository, UserService userService,
                             @Lazy BidService bidService, StatusService statusService,
                             StockExchangeService stockExchangeService) {
        this.brokerRepository = brokerRepository;
        this.userService = userService;
        this.bidService = bidService;
        this.statusService = statusService;
        this.stockExchangeService = stockExchangeService;
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
    public Page<UnemployedBrokerDTO> findAllUnemployed(String title, int page, int size, String[] sort) {
        Pageable pagingSort = PageRequest.of(page, size,Sort.by(sortType(sort)));

        Status status = statusService.find(StatusConst.UNEMPLOYED.getName());
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

    public Broker find(int id) {

        return brokerRepository.findBrokerByBrokerId(id);
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
        broker.setStatus(statusService.find(StatusConst.EMPLOYED.getName()));
        brokerRepository.save(broker);
        return new MessageResponse("ok");
    }

    @Override
    public MessageResponse dismiss(int brokerId) {
        Broker broker = brokerRepository.findById(brokerId).orElseThrow(
                () -> new NotFoundException("User error")
        );
        if (bidService.existsBrokerBid(broker.getId())){
            throw new NotFoundException("Error! Broker has active bids!");
        }
        broker.setEmployer(null);
        broker.setStatus(statusService.find(StatusConst.UNEMPLOYED.getName()));
        brokerRepository.save(broker);
        return new MessageResponse("ok");
    }

    @Override
    public Page<BrokerStatisticsDTO> getStatistics(int page, int size, int clientId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Broker> brokers = brokerRepository.findBrokersByEmployerId(clientId, pageable);

        List<BrokerStatisticsDTO> brokerStatisticsDTOs = new ArrayList<>();
        for (Broker broker : brokers) {
            int totalAmount = getTotalAmount(clientId, broker.getId());
            brokerStatisticsDTOs.add(new BrokerStatisticsDTO(transfertoDto(broker), totalAmount));
        }
        return new PageImpl<>(brokerStatisticsDTOs, brokers.getPageable(), brokers.getTotalElements());
    }

    @Override
    public void save(User user, String exchangeName) {
        Broker broker = new Broker();
        broker.setBroker(user);
        broker.setStatus(statusService.find(StatusConst.UNEMPLOYED.getName()));
        broker.setExchange(stockExchangeService.find(exchangeName));
        brokerRepository.save(broker);
    }

    @Override
    public MessageResponse update(int userId, String exchange) {
        User user = userService.findUser(userId);
        userService.changeRole(user, "ROLE_BROKER");
        save(user, exchange);
        return new MessageResponse("Role was changed");
    }

    private int getTotalAmount(int clientId, int brokerId) {
        List<Bid> bids = bidService.getCompletedBids(clientId, brokerId);
        int totalAmount = 0;
        for (Bid bid : bids) {
            totalAmount += bid.getAmount();
        }
        return totalAmount;
    }

    private List<Sort.Order> sortType(String[] sort) {
        List<Sort.Order> list = new ArrayList<>();
        for (int i = 0; i< sort.length;i++ ) {
            list.add(new Sort.Order(Sort.Direction.fromString(sort[i]), sort[++i]));
        }
        return list;
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
