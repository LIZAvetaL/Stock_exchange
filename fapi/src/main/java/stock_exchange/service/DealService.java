package stock_exchange.service;

import stock_exchange.model.Deal;
import stock_exchange.model.response.PageResponse;


public interface DealService {
    PageResponse<Deal> find(int page, int size, String[] sort, int brokerId);
}
