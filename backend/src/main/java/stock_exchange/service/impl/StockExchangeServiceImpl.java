package stock_exchange.service.impl;

import stock_exchange.model.StockExchange;
import stock_exchange.repository.StockExchangeRepository;
import stock_exchange.dto.StockExchangeDTO;
import stock_exchange.service.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockExchangeServiceImpl implements StockExchangeService {

    private final StockExchangeRepository stockExchangeRepository;

    @Autowired
    public StockExchangeServiceImpl(StockExchangeRepository stockExchangeRepository) {
        this.stockExchangeRepository = stockExchangeRepository;
    }

    @Override
    public List<StockExchangeDTO> findByOwner(int owner) {
        List<StockExchange> exchanges = stockExchangeRepository.findStockExchangeByOwner(owner);
        List<StockExchangeDTO> exchangeDTOs = new ArrayList<>();
        for (StockExchange exchange : exchanges) {
            StockExchangeDTO exchangeDTO = new StockExchangeDTO(exchange.getId(), exchange.getExchangeName(),
                    exchange.getCountry(), exchange.getCity(), exchange.getCreationDate(), exchange.getDescription());
            exchangeDTOs.add(exchangeDTO);
        }
        return exchangeDTOs;
    }
}
