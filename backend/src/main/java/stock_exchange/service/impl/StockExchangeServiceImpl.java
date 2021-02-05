package stock_exchange.service.impl;

import stock_exchange.dto.CreateStockExchangeDTO;
import stock_exchange.model.StockExchange;
import stock_exchange.repository.StockExchangeRepository;
import stock_exchange.dto.StockExchangeDTO;
import stock_exchange.response.MessageResponse;
import stock_exchange.service.StatusService;
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
    private final StatusService statusService;

    @Autowired
    public StockExchangeServiceImpl(StockExchangeRepository stockExchangeRepository, UserService userService,
                                    StatusService statusService) {
        this.stockExchangeRepository = stockExchangeRepository;
        this.userService = userService;
        this.statusService = statusService;
    }

    @Override
    public MessageResponse update(StockExchangeDTO exchangeDTO) {
        StockExchange exchange = transfer(exchangeDTO);
        stockExchangeRepository.save(exchange);
        return new MessageResponse("Stock exchange");
    }

    @Override
    public List<StockExchangeDTO> findByOwner(int ownerId) {
        List<StockExchange> exchanges = stockExchangeRepository.findStockExchangesByOwnerId(ownerId);
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

    @Override
    public MessageResponse changeStatus(int exchangeId, String statusName) {
        StockExchange exchange = stockExchangeRepository.findById(exchangeId).get();
        exchange.setStatus(statusService.find(statusName));
        stockExchangeRepository.save(exchange);
        return new MessageResponse("Status was changed");
    }

    @Override
    public MessageResponse create(int ownerId, CreateStockExchangeDTO exchange) {
        stockExchangeRepository.save(transfer(exchange, ownerId));
        return new MessageResponse("ok");
    }

    private StockExchangeDTO transfer(StockExchange exchange) {
        return new StockExchangeDTO(exchange.getId(), exchange.getExchangeName(),
                exchange.getCountry(), exchange.getCity(), exchange.getCreationDate(),
                exchange.getDescription(), exchange.getStatus().getStatusName(),
                exchange.getOwner().getName());
    }

    private StockExchange transfer(StockExchangeDTO exchangeDTO) {
        return new StockExchange(exchangeDTO.getId(), exchangeDTO.getExchangeName(),
                exchangeDTO.getCountry(), exchangeDTO.getCity(), exchangeDTO.getCreationDate(),
                exchangeDTO.getDescription(), statusService.find(exchangeDTO.getStatus()),
                userService.findUser(exchangeDTO.getOwner()));
    }
    private StockExchange transfer(CreateStockExchangeDTO exchangeDTO, int id) {
        StockExchange exchange = new StockExchange();
        exchange.setExchangeName(exchangeDTO.getExchangeName());
        exchange.setCountry(exchangeDTO.getCountry());
        exchange.setCity(exchangeDTO.getCity());
        exchange.setCreationDate(exchangeDTO.getCreationDate());
        exchange.setDescription(exchangeDTO.getDescription());
        exchange.setStatus(statusService.find(exchangeDTO.getStatus()));
        exchange.setOwner(userService.findUser(id));
        return exchange;
    }
}
