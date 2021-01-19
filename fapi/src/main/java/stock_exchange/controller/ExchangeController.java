package stock_exchange.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stock_exchange.service.ExchangeService;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    private final ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity findAll(@PathVariable(name = "ownerId") int ownerId) {
        return new ResponseEntity(exchangeService.findAll(ownerId), HttpStatus.OK);
    }

    @GetMapping("find/{exchangeId}")
    public ResponseEntity find(@PathVariable(name = "exchangeId") int exchangeId) {
        return new ResponseEntity(exchangeService.find(exchangeId), HttpStatus.OK);
    }
}
