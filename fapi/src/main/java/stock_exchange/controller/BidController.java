package stock_exchange.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stock_exchange.service.BidService;

@RestController
@RequestMapping("/bid")
public class BidController {
    private final BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @GetMapping("find/brokers-bids")
    public ResponseEntity findBrokersBids(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam int brokerId) {
        return new ResponseEntity(bidService.findBrokersBids(page, size, brokerId), HttpStatus.OK);
    }

    @GetMapping("find/clients-bids")
    public ResponseEntity indBrokersBids(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam int clientId) {
        return new ResponseEntity(bidService.findClientsBids(page, size, clientId), HttpStatus.OK);
    }
}
