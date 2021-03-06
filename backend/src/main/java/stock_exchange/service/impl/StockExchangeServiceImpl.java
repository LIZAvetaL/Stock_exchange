package stock_exchange.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import stock_exchange.config.StatusConst;
import stock_exchange.dto.CreateStockExchangeDTO;
import stock_exchange.exception.NotFoundException;
import stock_exchange.model.OwnerStatistics;
import stock_exchange.model.StockExchange;
import stock_exchange.repository.StockExchangeRepository;
import stock_exchange.dto.StockExchangeDTO;
import stock_exchange.response.MessageResponse;
import stock_exchange.service.StatusService;
import stock_exchange.service.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stock_exchange.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<StockExchangeDTO> findAll() {
        return stockExchangeRepository.findStockExchangesByStatusStatusName(
                StatusConst.OPEN.getName())
                .stream()
                .map(this::transfer)
                .collect(Collectors.toList());
    }

    @Override
    public MessageResponse update(StockExchangeDTO exchangeDTO) {
        StockExchange exchange = transfer(exchangeDTO);
        stockExchangeRepository.save(exchange);
        return new MessageResponse("Stock exchange has been updated");
    }

    @Override
    public Page<StockExchangeDTO> findByOwner(int page, int size, String[] sort, int ownerId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortType(sort)));
        return stockExchangeRepository.findStockExchangesByOwnerId(ownerId, pageable)
                .map(this::transfer);
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
        return new MessageResponse("Status has been changed");
    }

    @Override
    public MessageResponse create(int ownerId, CreateStockExchangeDTO exchange) {
        if (!stockExchangeRepository.existsStockExchangeByExchangeName(exchange.getExchangeName())) {
            stockExchangeRepository.save(transfer(exchange, ownerId));
            return new MessageResponse("Stock exchange has been created");
        } else {
            throw new NotFoundException("Name is already taken");
        }
    }

    @Override
    public StockExchange find(String name) {
        return stockExchangeRepository.findStockExchangeByExchangeName(name);
    }

    @Override
    public Page<OwnerStatistics> getStatistics(int exchangeId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
       Page<OwnerStatistics> statistics= stockExchangeRepository.findStatistics(exchangeId, pageable)
        .map(item -> new OwnerStatistics((LocalDate) item[0],(Long) item[1]));

        return statistics;
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
        exchange.setCreationDate(LocalDate.now());
        exchange.setDescription(exchangeDTO.getDescription());
        exchange.setStatus(statusService.find(StatusConst.OPEN.getName()));
        exchange.setOwner(userService.findUser(id));
        return exchange;
    }

    private List<Sort.Order> sortType(String[] sort) {
        List<Sort.Order> list = new ArrayList<>();
        for (int i = 0; i < sort.length; i++) {
            list.add(new Sort.Order(Sort.Direction.fromString(sort[i]), sort[++i]));
        }
        return list;
    }
}
