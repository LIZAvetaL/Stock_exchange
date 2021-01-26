package stock_exchange.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import stock_exchange.dto.BidDTO;
import stock_exchange.model.Bid;;
import stock_exchange.repository.BidRepository;
import stock_exchange.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stock_exchange.service.UserService;


@Service
public class BidServiceImpl implements BidService {
    private final BidRepository bidRepository;
    private final UserService userService;

    @Autowired
    public BidServiceImpl(BidRepository bidRepository, UserService userService) {
        this.bidRepository = bidRepository;
        this.userService = userService;
    }

    @Override
    public Bid getBid(int id) {
        return bidRepository.findById(id).get();
    }

    @Override
    public Page<BidDTO> findBrokersBids(int page, int size, int brokerId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Bid> bids = bidRepository.findBidsByBrokerId(brokerId, pageable);
        return bids.map(BidServiceImpl::transfer);
    }

    @Override
    public Page<BidDTO> findClientsBids(int page, int size, int clientId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Bid> bids = bidRepository.findBidsByClientId(clientId, pageable);
        return bids.map(BidServiceImpl::transfer);
    }

    private static BidDTO transfer(Bid bid) {
        BidDTO bidDTO = new BidDTO();
        bidDTO.setId(bid.getId());
        bidDTO.setAmount(bid.getAmount());
        bidDTO.setIssuer(bid.getIssuer());
        bidDTO.setBidNumber(bid.getBidNumber());
        bidDTO.setMaxPrice(bid.getMaxPrice());
        bidDTO.setMinPrice(bid.getMinPrice());
        bidDTO.setPriority(bid.getPriority());
        bidDTO.setCreationDate(bid.getCreationDate().toString());
        bidDTO.setDueDate(bid.getDueDate().toString());
        bidDTO.setStatus(bid.getStatus().getStatusName());
        bidDTO.setBroker(bid.getBroker().getBroker().getName());
        return bidDTO;
    }
}
