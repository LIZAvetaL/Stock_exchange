package stock_exchange.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import stock_exchange.dto.StockExchangeDTO;
import stock_exchange.service.StockExchangeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exchange")
public class StockExchangeController {

    private final StockExchangeService stockExchangeService;

    public StockExchangeController(StockExchangeService stockExchangeService) {
        this.stockExchangeService = stockExchangeService;
    }

    @GetMapping("/{owner-id}")
    public ResponseEntity findByOwner(@PathVariable("owner-id") int owner) {
        return new ResponseEntity(stockExchangeService.findByOwner(owner), HttpStatus.OK);
    }
    @GetMapping("find/{exchangeId}")
    public ResponseEntity find(@PathVariable(name = "exchangeId") int exchangeId) {
        return new ResponseEntity(stockExchangeService.find(exchangeId), HttpStatus.OK);
    }
}
