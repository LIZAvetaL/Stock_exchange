package stock_exchange.service;

import org.springframework.data.domain.Page;
import stock_exchange.dto.DealDTO;
import stock_exchange.model.Bid;
import stock_exchange.model.Deal;

public interface DealService {

    int create(Bid sellerBid, Bid buyerBid, double price);

    Page<DealDTO> find(int page, int size, String[] sort, int brokerId);
}
