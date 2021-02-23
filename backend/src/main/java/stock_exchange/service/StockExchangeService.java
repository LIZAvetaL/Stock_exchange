package stock_exchange.service;

import org.springframework.data.domain.Page;
import stock_exchange.dto.CreateStockExchangeDTO;
import stock_exchange.dto.StockExchangeDTO;
import stock_exchange.model.OwnerStatistics;
import stock_exchange.model.StockExchange;
import stock_exchange.response.MessageResponse;

import java.util.List;

public interface StockExchangeService {
    List<StockExchangeDTO> findAll();

    MessageResponse update(StockExchangeDTO exchange);

    Page<StockExchangeDTO> findByOwner(int page, int size, String[] sort, int owner);

    StockExchangeDTO find(int exchangeId);

    MessageResponse changeStatus(int exchangeId, String status);

    MessageResponse create(int ownerId, CreateStockExchangeDTO exchange);

    StockExchange find(String name);

    Page<OwnerStatistics> getStatistics(int exchangeId, int page, int size);
}
