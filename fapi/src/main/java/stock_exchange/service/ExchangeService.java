package stock_exchange.service;

import stock_exchange.model.CreateStockExchange;
import stock_exchange.model.OwnerStatistics;
import stock_exchange.model.StockExchange;
import stock_exchange.model.response.MessageResponse;
import stock_exchange.model.response.PageResponse;

import java.util.List;

public interface ExchangeService {
    PageResponse<StockExchange> findAll(int page, int size, String[] sort, int ownerId);

    List<StockExchange> findAll();

    StockExchange find(int exchangeId);

    MessageResponse changeStatus(int exchangeId, String status);

    MessageResponse update(StockExchange exchange);

    MessageResponse create(int ownerId, CreateStockExchange exchange);

    PageResponse<OwnerStatistics> getStatistics(int exchangeId, int page, int size);
}
