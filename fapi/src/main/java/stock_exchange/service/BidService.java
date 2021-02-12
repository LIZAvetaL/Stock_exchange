package stock_exchange.service;

import stock_exchange.model.Bid;
import stock_exchange.model.BrokerBid;
import stock_exchange.model.request.CreateBid;
import stock_exchange.model.response.MessageResponse;
import stock_exchange.model.response.PageResponse;


public interface BidService {
    PageResponse<Bid> findBrokersBids(int page, int size, String[] sort,int brokerId);

    PageResponse<Bid> findClientsBids(int page, int size,String[] sort, int clientId);

    MessageResponse create(int id, CreateBid createBid);

    MessageResponse update(Bid bid);

    Bid find(int id);

    PageResponse<BrokerBid> findBids(int page, int size, String[] sort, int bidId);

    MessageResponse createDeal(int sellerBidId, int buyerBidId, double price);

    PageResponse<Bid> findAll(int page, int size, String[] sort);
}
