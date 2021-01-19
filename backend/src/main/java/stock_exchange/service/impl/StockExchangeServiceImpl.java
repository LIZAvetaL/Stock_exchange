package stock_exchange.service.impl;

import stock_exchange.dto.UserDTO;
import stock_exchange.model.StockExchange;
import stock_exchange.model.User;
import stock_exchange.repository.StockExchangeRepository;
import stock_exchange.dto.StockExchangeDTO;
import stock_exchange.service.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stock_exchange.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockExchangeServiceImpl implements StockExchangeService {

    private final StockExchangeRepository stockExchangeRepository;
    private final UserService userService;

    @Autowired
    public StockExchangeServiceImpl(StockExchangeRepository stockExchangeRepository, UserService userService) {
        this.stockExchangeRepository = stockExchangeRepository;
        this.userService = userService;
    }

    @Override
    public List<StockExchangeDTO> findByOwner(int ownerId) {
        List<StockExchange> exchanges = stockExchangeRepository.findStockExchangesByOwner(userService.findUser(ownerId));
        List<StockExchangeDTO> exchangeDTOs = new ArrayList<>();
        for (StockExchange exchange : exchanges) {
            StockExchangeDTO exchangeDTO = transfer(exchange);
            exchangeDTOs.add(exchangeDTO);
        }
        return exchangeDTOs;
    }

    @Override
    public StockExchangeDTO find(int exchangeId) {
        return transfer(stockExchangeRepository.findById(exchangeId).get());
    }

    private StockExchangeDTO transfer(StockExchange exchange) {
        return new StockExchangeDTO(exchange.getId(), exchange.getExchangeName(),
                exchange.getCountry(), exchange.getCity(), exchange.getCreationDate().toString(), exchange.getDescription());
    }
}
