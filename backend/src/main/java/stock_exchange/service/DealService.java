package stock_exchange.service;

import stock_exchange.model.Bid;
import stock_exchange.model.Deal;

public interface DealService {
    Deal getDeal(int buyerBidId);

    void save(Deal deal);

    void create(Bid sellerBid, Bid buyerBid, double price);
}
