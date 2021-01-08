package stock_exchange.service;

import stock_exchange.dto.StockExchangeDTO;

import java.util.List;

public interface StockExchangeService {
    List<StockExchangeDTO> findByOwner(int owner);
}
