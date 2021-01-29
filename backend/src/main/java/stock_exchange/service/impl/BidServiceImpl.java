package stock_exchange.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import stock_exchange.dto.BidDTO;
import stock_exchange.dto.CreateBidDTO;
import stock_exchange.model.Bid;;
import stock_exchange.repository.BidRepository;
import stock_exchange.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stock_exchange.service.BrokerService;
import stock_exchange.service.StatusService;
import stock_exchange.service.UserService;

import java.time.LocalDate;
import java.util.Random;


@Service
public class BidServiceImpl implements BidService {
    private final BidRepository bidRepository;
    private final UserService userService;
    private final StatusService statusService;
    private final BrokerService brokerService;

    @Autowired
    public BidServiceImpl(BidRepository bidRepository, UserService userService,
                          StatusService statusService, BrokerService brokerService) {
        this.bidRepository = bidRepository;
        this.userService = userService;
        this.statusService=statusService;
        this.brokerService=brokerService;
    }

    @Override
    public Bid getBid(int id) {
        return bidRepository.findById(id).get();
    }

    @Override
    public Page<BidDTO> findBrokersBids(int page, int size, int brokerId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Bid> bids = bidRepository.findBidsByBrokerId(brokerId, pageable);
        return bids.map(this::transfer);
    }

    @Override
    public Page<BidDTO> findClientsBids(int page, int size, int clientId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Bid> bids = bidRepository.findBidsByClientId(clientId, pageable);
        return bids.map(this::transfer);
    }

    @Override
    public void create(int clientId, CreateBidDTO createBid) {
        Bid bid= transfer(createBid);
        bid.setBidNumber(generateRandom());
        bid.setCreationDate(LocalDate.now());
        bid.setClient(userService.findUser(clientId));
        bidRepository.save(bid);
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

    public BidDTO transfer(Bid bid) {
        BidDTO bidDTO = new BidDTO();
        bidDTO.setId(bid.getId());
        bidDTO.setAmount(bid.getAmount());
        bidDTO.setIssuer(bid.getIssuer());
        bidDTO.setBidNumber(bid.getBidNumber());
        bidDTO.setMaxPrice(bid.getMaxPrice());
        bidDTO.setMinPrice(bid.getMinPrice());
        bidDTO.setPriority(bid.getPriority());
        bidDTO.setCreationDate(bid.getCreationDate());
        bidDTO.setDueDate(bid.getDueDate());
        bidDTO.setStatus(bid.getStatus().getStatusName());
        bidDTO.setBroker(bid.getBroker().getBroker().getName());
        return bidDTO;
    }

    private Bid transfer(CreateBidDTO createBidDTO){
        Bid bid=new Bid();
        bid.setIssuer(createBidDTO.getIssuer());
        bid.setAmount(createBidDTO.getAmount());
        bid.setMinPrice(createBidDTO.getMinPrice());
        bid.setMaxPrice(createBidDTO.getMaxPrice());
        bid.setPriority(createBidDTO.getPriority());
        bid.setDueDate(createBidDTO.getDueDate());
        bid.setStatus(statusService.find(createBidDTO.getStatus()));
        bid.setBroker(brokerService.findBroker(createBidDTO.getBroker()));
        return bid;
    }

}
