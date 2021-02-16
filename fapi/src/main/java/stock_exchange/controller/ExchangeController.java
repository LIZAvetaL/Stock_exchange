package stock_exchange.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stock_exchange.model.CreateStockExchange;
import stock_exchange.model.StockExchange;
import stock_exchange.model.response.MessageResponse;
import stock_exchange.service.ExchangeService;


@RestController
@RequestMapping("/exchange/")
public class ExchangeController {

    private final ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("{ownerId}")
    public ResponseEntity findAll(@PathVariable(name = "ownerId") int ownerId) {
        return new ResponseEntity(exchangeService.findAll(ownerId), HttpStatus.OK);
    }

    @GetMapping("find/{exchangeId}")
    public ResponseEntity find(@PathVariable(name = "exchangeId") int exchangeId) {
        return new ResponseEntity(exchangeService.find(exchangeId), HttpStatus.OK);
    }

    @PostMapping("change-status")
    public ResponseEntity<MessageResponse> changeStatus(@RequestParam(name = "id") int exchangeId,
                                                        @RequestParam String status) {
        MessageResponse response = exchangeService.changeStatus(exchangeId, status);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity<MessageResponse> update(@RequestBody StockExchange exchange) {
        MessageResponse response = exchangeService.update(exchange);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<MessageResponse> create(@RequestParam(name = "owner-id") int ownerId,
                                                  @RequestBody CreateStockExchange exchange) {
        MessageResponse response = exchangeService.create(ownerId, exchange);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
