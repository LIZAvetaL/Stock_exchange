package stock_exchange.service;

import stock_exchange.model.Bid;

import java.util.List;

public interface BidService {
    List<Bid> findBrokersBids(int id);

    Bid getBid(int sellerBidId);
}
