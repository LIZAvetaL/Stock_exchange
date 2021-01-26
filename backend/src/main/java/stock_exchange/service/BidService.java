package stock_exchange.service;

import org.springframework.data.domain.Page;
import stock_exchange.dto.BidDTO;
import stock_exchange.model.Bid;

import java.util.List;

public interface BidService {
    Page<BidDTO> findBrokersBids(int page, int size,int brokerId);
    Bid getBid(int sellerBidId);
    Page<BidDTO> findClientsBids(int page, int size, int clientId);
}
