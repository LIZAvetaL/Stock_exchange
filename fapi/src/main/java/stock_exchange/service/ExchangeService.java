package stock_exchange.service;

import stock_exchange.model.StockExchange;
import stock_exchange.model.response.MessageResponse;

import java.util.List;

public interface ExchangeService {
    List<StockExchange> findAll(int ownerId);

    StockExchange find(int exchangeId);

    MessageResponse changeStatus(int exchangeId, String status);

    MessageResponse update(StockExchange exchange);

    MessageResponse create(int ownerId, StockExchange exchange);
}
