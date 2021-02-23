package stock_exchange.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import stock_exchange.dto.CreateStockExchangeDTO;
import stock_exchange.dto.StockExchangeDTO;
import stock_exchange.model.OwnerStatistics;
import stock_exchange.response.MessageResponse;
import stock_exchange.service.StockExchangeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchange")
public class StockExchangeController {

    private final StockExchangeService stockExchangeService;

    public StockExchangeController(StockExchangeService stockExchangeService) {
        this.stockExchangeService = stockExchangeService;
    }

    @GetMapping
    public ResponseEntity<Page<StockExchangeDTO>> findAllByOwner(@RequestParam int page,
                                                                 @RequestParam int size,
                                                                 @RequestParam String[] sort,
                                                                 @RequestParam int ownerId) {
        return new ResponseEntity(stockExchangeService.findByOwner(page, size, sort, ownerId), HttpStatus.OK);
    }

    @GetMapping("find/statistics")
    public ResponseEntity<Page<OwnerStatistics>> findStatistic(@RequestParam int page,
                                                               @RequestParam int size,
                                                               @RequestParam int exchangeId) {
        return new ResponseEntity(stockExchangeService.getStatistics(exchangeId, page, size), HttpStatus.OK);
    }

    @GetMapping("find/{exchangeId}")
    public ResponseEntity<StockExchangeDTO> find(@PathVariable(name = "exchangeId") int exchangeId) {
        return new ResponseEntity(stockExchangeService.find(exchangeId), HttpStatus.OK);
    }

    @GetMapping("find/all")
    public ResponseEntity findAll() {
        return new ResponseEntity(stockExchangeService.findAll(), HttpStatus.OK);
    }

    @PostMapping("change-status")
    public ResponseEntity<MessageResponse> changeStatus(@RequestParam(name = "id") int exchangeId,
                                                        @RequestParam String status) {
        MessageResponse response = stockExchangeService.changeStatus(exchangeId, status);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity<MessageResponse> update(@RequestBody StockExchangeDTO exchange) {
        MessageResponse response = stockExchangeService.update(exchange);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<MessageResponse> create(@RequestParam(name = "owner-id") int ownerId,
                                                  @RequestBody CreateStockExchangeDTO exchange) {
        MessageResponse response = stockExchangeService.create(ownerId, exchange);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
