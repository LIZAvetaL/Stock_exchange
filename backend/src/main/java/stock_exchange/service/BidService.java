package stock_exchange.service;

import org.springframework.data.domain.Page;
import stock_exchange.dto.BidDTO;
import stock_exchange.dto.CreateBidDTO;
import stock_exchange.model.Bid;
import stock_exchange.response.MessageResponse;

import java.util.List;
import java.util.Map;

public interface BidService {
    Page<BidDTO> findBrokersBids(int page, int size,int brokerId);
    Bid getBid(int sellerBidId);
    Page<BidDTO> findClientsBids(int page, int size, String[] sort, int clientId);

    void create(int id, CreateBidDTO createBid);

    MessageResponse update(BidDTO bid);

    BidDTO find(int id);
}
