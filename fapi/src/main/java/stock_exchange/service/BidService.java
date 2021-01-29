package stock_exchange.service;

import stock_exchange.model.Bid;
import stock_exchange.model.request.CreateBid;
import stock_exchange.model.response.PageResponse;

public interface BidService {
    PageResponse<Bid> findBrokersBids(int page, int size,int brokerId);

    PageResponse<Bid> findClientsBids(int page, int size,int clientId);

    void create(int id, CreateBid createBid);
}
