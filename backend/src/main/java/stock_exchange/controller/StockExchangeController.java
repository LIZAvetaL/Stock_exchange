package stock_exchange.controller;

import stock_exchange.dto.StockExchangeDTO;
import stock_exchange.service.StockExchangeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stock-exchange")
public class StockExchangeController {

    private final StockExchangeService stockExchangeService;

    public StockExchangeController(StockExchangeService stockExchangeService) {
        this.stockExchangeService = stockExchangeService;
    }

    @GetMapping("/{owner-id}")
    public List<StockExchangeDTO> findByOwner(@PathVariable("owner-id") int owner) {
        return stockExchangeService.findByOwner(owner);
    }

}
