package stock_exchange.service;

import stock_exchange.model.StockExchange;

import java.util.List;

public interface ExchangeService {
    List<StockExchange> findAll(int ownerId);

    StockExchange find(int exchangeId);
}
