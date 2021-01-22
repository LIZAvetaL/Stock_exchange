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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Bid sellerBid = bidService.getBid(sellerBidId);
        Bid buyerBid = bidService.getBid(buyerBidId);
        Deal deal = new Deal();
        dealService.save(deal);
        return deal;
    }

    @Override
    public Map<String, Object> findAllUnemployed(String title, int page, int size, String sort) {
        Pageable pagingSort = PageRequest.of(page, size, Sort.by(sort));
        Page<Broker> tempPage;
        Page<BrokerDTO> pageBrokers ;
        Status status = statusService.find("unemployed");
        tempPage = brokerRepository.findAllByStatus(status, pagingSort);
        pageBrokers= tempPage.map(BrokerServiceImpl::transfer);

        Map<String, Object> response = new HashMap<>();
        response.put("brokers", pageBrokers.getContent());
        response.put("currentPage", pageBrokers.getNumber());
        response.put("totalItems", pageBrokers.getTotalElements());
        response.put("totalPages", pageBrokers.getTotalPages());

        return  response;
    }

    @Override
    public BrokerDTO findBroker(int id) {
        return transfer(brokerRepository.findById(id).get());
    }

    private static BrokerDTO transfer(Broker broker) {
        return new BrokerDTO(broker.getId(), broker.getBroker().getName(),
                broker.getEmployer().getName(), broker.getStatus().getStatusName(),
                broker.getExchange().getExchangeName());
    }
}
