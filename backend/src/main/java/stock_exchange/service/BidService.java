package stock_exchange.service;

import org.springframework.data.domain.Page;
import org.springframework.data.util.Streamable;
import stock_exchange.dto.BidDTO;
import stock_exchange.dto.BrokerBidDTO;
import stock_exchange.dto.CreateBidDTO;
import stock_exchange.model.Bid;
import stock_exchange.model.Deal;
import stock_exchange.response.MessageResponse;

public interface BidService {
    Page<BidDTO> findBrokersBids(int page, int size, String[] sort,int brokerId);
    Bid getBid(int sellerBidId);
    Page<BidDTO> findClientsBids(int page, int size, String[] sort, int clientId);

    void create(int id, CreateBidDTO createBid);

    MessageResponse update(BidDTO bid);

    BidDTO find(int id);

    Streamable<BrokerBidDTO> findBids(int page, int size, String[] sort, int bidId);

    MessageResponse createDeal(int sellerBidId, int buyerBidId, double price);

    Page<BidDTO> findAll(int page, int size, String[] sort);
}
