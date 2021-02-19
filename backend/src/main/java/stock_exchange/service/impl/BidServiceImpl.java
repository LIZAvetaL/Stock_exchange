package stock_exchange.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import stock_exchange.config.StatusConst;
import stock_exchange.dto.BidDTO;
import stock_exchange.dto.BrokerBidDTO;
import stock_exchange.dto.CreateBidDTO;
import stock_exchange.exception.BusinessException;
import stock_exchange.exception.NotFoundException;
import stock_exchange.model.Bid;;
import stock_exchange.model.Broker;
import stock_exchange.model.Deal;
import stock_exchange.repository.BidRepository;
import stock_exchange.response.MessageResponse;
import stock_exchange.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stock_exchange.service.BrokerService;
import stock_exchange.service.DealService;
import stock_exchange.service.StatusService;
import stock_exchange.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;
    private final UserService userService;
    private final StatusService statusService;
    private final BrokerService brokerService;
    private final DealService dealService;

    @Autowired
    public BidServiceImpl(BidRepository bidRepository, UserService userService,
                          StatusService statusService, BrokerService brokerService,
                          DealService dealService) {
        this.bidRepository = bidRepository;
        this.userService = userService;
        this.statusService = statusService;
        this.brokerService = brokerService;
        this.dealService = dealService;
    }

    @Override
    public Bid getBid(int id) {
        return bidRepository.findById(id).get();
    }

    @Override
    public Page<BidDTO> findBrokersBids(int page, int size, String[] sort, int brokerId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortType(sort)));
        Broker broker = brokerService.find(brokerId);
        Page<Bid> bids = bidRepository.findBidsByBrokerIdAndStatusStatusName(broker.getId(),
                StatusConst.ACTIVE.getName(), pageable);
        return bids.map(this::transfer);
    }

    @Override
    public Page<BidDTO> findClientsBids(int page, int size, String[] sort, int clientId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortType(sort)));
        Page<Bid> bids = bidRepository.findBidsByClientId(clientId, pageable);
        return bids.map(this::transfer);
    }

    private List<Sort.Order> sortType(String[] sort) {
        List<Sort.Order> list = new ArrayList<>();
        for (int i = 0; i< sort.length;i++ ) {
            list.add(new Sort.Order(Sort.Direction.fromString(sort[i]), sort[++i]));
        }
        return list;
    }

    @Override
    public void create(int clientId, CreateBidDTO createBid) {
        Bid bid = transfer(createBid);
        bid.setBidNumber(generateRandom());
        bid.setCreationDate(LocalDate.now());
        bid.setClient(userService.findUser(clientId));
        bidRepository.save(bid);
    }

    @Override
    public MessageResponse update(BidDTO bidDTO) {
        bidRepository.save(transfer(bidDTO));
        return new MessageResponse("Bid has been edited");
    }

    @Override
    public BidDTO find(int id) {
        Bid bid = bidRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Bid hasn't been found")
        );
        return transfer(bid);
    }

    @Override
    public Page<BrokerBidDTO> findBids(int page, int size, String[] sort, int bidId) {
        Bid bid = bidRepository.findById(bidId).orElseThrow(
                () -> new NotFoundException("Bid error")
        );
        String type = type(bid.getType());
        List<BrokerBidDTO> bids = bidRepository.findBidsByStatusStatusNameAndIssuerAndType(
                StatusConst.ACTIVE.getName(), bid.getIssuer(), type)
                .stream()
                .filter(item -> checkPrice(item, bid))
                .map(item -> transfer(item, bid.getMinPrice()))
                .collect(Collectors.toList());
        return new PageImpl<>(bids);
    }

    @Override
    public MessageResponse createDeal(int sellerBidId, int buyerBidId, double price) {

        Bid sellerBid = bidRepository.findById(sellerBidId)
                .orElseThrow(
                        () -> new NotFoundException("Bid error")
                );
        Bid buyerBid = bidRepository.findById(buyerBidId)
                .orElseThrow(
                        () -> new NotFoundException("Bid error")
                );

        dealService.create(sellerBid, buyerBid, price);

        if (sellerBid.getAmount() > buyerBid.getAmount()) {
            changeAmount(sellerBid,  buyerBid.getAmount());
            changeStatus(buyerBid);
        }
        if (sellerBid.getAmount() < buyerBid.getAmount()) {
            changeAmount(buyerBid,  sellerBid.getAmount());
            changeStatus(sellerBid);
        } else {
            changeStatus(sellerBid);
            changeStatus(buyerBid);
        }
        return new MessageResponse("");
    }

    @Override
    public Page<BidDTO> findAll(String issuer, int page, int size, String[] sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortType(sort)));

        if ("".equals(issuer)){
            return bidRepository.findBidsByStatusStatusName(StatusConst.ACTIVE.getName(), pageable)
                    .map(this::transfer);
        }else{
            return bidRepository.findBidsByStatusStatusNameAndIssuer(StatusConst.ACTIVE.getName(), issuer, pageable)
                    .map(this::transfer);
        }
    }

    @Override
    public List<Bid> getCompletedBids(int clientId, int brokerId) {
        return bidRepository.findBidsByClientIdAndBrokerIdAndStatusStatusName(
                clientId, brokerId, StatusConst.COMPLETED.getName());
    }

    @Override
    public boolean existsBrokerBid(int brokerId) {
        return bidRepository.existsBidsByBrokerIdAndStatusStatusName(brokerId, StatusConst.ACTIVE.getName());
    }

    private void changeStatus(Bid bid) {
        bid.setStatus(statusService.find(StatusConst.COMPLETED.getName()));
        bidRepository.save(bid);
    }

    private void changeAmount(Bid bid, int amount) {
        create(bid, amount);
        bid.setAmount(amount);
        bid.setStatus(statusService.find(StatusConst.COMPLETED.getName()));
        bidRepository.save(bid);
    }

    private void create(Bid bid, int amount){
        Bid newBid =new Bid();
        newBid.setAmount(bid.getAmount()-amount);
        newBid.setStatus(bid.getStatus());
        newBid.setType(bid.getType());
        newBid.setMinPrice(bid.getMinPrice());
        newBid.setMaxPrice(bid.getMaxPrice());
        newBid.setClient(bid.getClient());
        newBid.setBroker(bid.getBroker());
        newBid.setPriority(bid.getPriority());
        newBid.setIssuer(bid.getIssuer());
        newBid.setBidNumber(generateRandom());
        bidRepository.save(newBid);
    }


    private String type(String type) {
        if (StatusConst.BUY.getName().equals(type)) {
            return StatusConst.SALE.getName();
        } else return StatusConst.BUY.getName();
    }

    private boolean checkPrice(Bid tempBid, Bid bid) {
        if (bid.getMinPrice() >= tempBid.getMinPrice() && bid.getMinPrice() <= tempBid.getMaxPrice()) {
            return true;
        }

        if (bid.getMaxPrice() >= tempBid.getMinPrice() && bid.getMaxPrice() <= tempBid.getMaxPrice()) {
            return true;
        }

        if (bid.getMaxPrice() < tempBid.getMinPrice() && bid.getMaxPrice() > tempBid.getMaxPrice()) {
            return true;
        }
        return false;
    }

    private long generateRandom() {
        Random random = new Random();
        char[] digits = new char[12];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < 12; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return Long.parseLong(new String(digits));
    }

    public BrokerBidDTO transfer(Bid bid, double price) {
        BrokerBidDTO bidDTO = new BrokerBidDTO();
        bidDTO.setId(bid.getId());
        bidDTO.setAmount(bid.getAmount());
        bidDTO.setIssuer(bid.getIssuer());
        bidDTO.setBidNumber(bid.getBidNumber());
        if (price >= bid.getMinPrice() && price <= bid.getMaxPrice()) {
            bidDTO.setPrice(price);
        } else {
            bidDTO.setPrice(bid.getMinPrice());
        }
        bidDTO.setBroker(bid.getBroker().getBroker().getName());
        return bidDTO;
    }

    public BidDTO transfer(Bid bid) {
        BidDTO bidDTO = new BidDTO();
        bidDTO.setId(bid.getId());
        bidDTO.setAmount(bid.getAmount());
        bidDTO.setIssuer(bid.getIssuer());
        bidDTO.setBidNumber(bid.getBidNumber());
        bidDTO.setMaxPrice(bid.getMaxPrice());
        bidDTO.setMinPrice(bid.getMinPrice());
        bidDTO.setPriority(bid.getPriority());
        bidDTO.setType(bid.getType());
        bidDTO.setCreationDate(bid.getCreationDate());
        bidDTO.setDueDate(bid.getDueDate());
        bidDTO.setStatus(bid.getStatus().getStatusName());
        bidDTO.setBroker(bid.getBroker().getBroker().getName());
        bidDTO.setClient(bid.getClient().getName());
        return bidDTO;
    }

    public Bid transfer(BidDTO bidDTO) {
        Bid bid = new Bid();
        bid.setId(bidDTO.getId());
        bid.setAmount(bidDTO.getAmount());
        bid.setIssuer(bidDTO.getIssuer());
        bid.setBidNumber(bidDTO.getBidNumber());
        bid.setMaxPrice(bidDTO.getMaxPrice());
        bid.setMinPrice(bidDTO.getMinPrice());
        bid.setPriority(bidDTO.getPriority());
        bid.setType(bidDTO.getType());
        bid.setCreationDate(bidDTO.getCreationDate());
        bid.setDueDate(bidDTO.getDueDate());
        bid.setStatus(statusService.find(bidDTO.getStatus()));
        bid.setBroker(brokerService.findBroker(bidDTO.getBroker()));
        bid.setClient(userService.findUser(bidDTO.getClient()));
        return bid;
    }

    private Bid transfer(CreateBidDTO createBidDTO) {
        Bid bid = new Bid();
        bid.setIssuer(createBidDTO.getIssuer());
        bid.setAmount(createBidDTO.getAmount());
        bid.setMinPrice(createBidDTO.getMinPrice());
        bid.setMaxPrice(createBidDTO.getMaxPrice());
        bid.setPriority(createBidDTO.getPriority());
        bid.setType(createBidDTO.getType());
        bid.setDueDate(createBidDTO.getDueDate());
        bid.setStatus(statusService.find(createBidDTO.getStatus()));
        bid.setBroker(brokerService.findBroker(createBidDTO.getBroker()));
        return bid;
    }

}
