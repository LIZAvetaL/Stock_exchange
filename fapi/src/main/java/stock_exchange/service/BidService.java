package stock_exchange.service;

import stock_exchange.model.Bid;
import stock_exchange.model.request.CreateBid;
import stock_exchange.model.response.MessageResponse;
import stock_exchange.model.response.PageResponse;

import java.util.Map;

public interface BidService {
    PageResponse<Bid> findBrokersBids(int page, int size,int brokerId);

    PageResponse<Bid> findClientsBids(int page, int size,String[] sort, int clientId);

    void create(int id, CreateBid createBid);

    MessageResponse update(Bid bid);

    Bid find(int id);
}
