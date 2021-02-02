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
import stock_exchange.model.Bid;
import stock_exchange.model.request.CreateBid;
import stock_exchange.model.response.MessageResponse;
import stock_exchange.service.BidService;

import java.util.Map;

@RestController
@RequestMapping("/bid")
public class BidController {
    private final BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @GetMapping("/find")
    public ResponseEntity<Bid> find(@RequestParam int id){
        return new ResponseEntity(bidService.find(id), HttpStatus.OK);
    }

    @GetMapping("/find/brokers-bids")
    public ResponseEntity findBrokersBids(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam int brokerId) {
        return new ResponseEntity(bidService.findBrokersBids(page, size, brokerId), HttpStatus.OK);
    }

    @GetMapping("/find/clients-bids")
    public ResponseEntity findClientsBids(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                          @RequestParam String[] sort,
                                         @RequestParam int clientId) {
        return new ResponseEntity(bidService.findClientsBids(page, size, sort, clientId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public  ResponseEntity create(@RequestParam int id,
                                  @RequestBody CreateBid createBid){
        bidService.create(id, createBid);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/update")
    public  ResponseEntity<MessageResponse> update(@RequestBody Bid bid){

        return new ResponseEntity(bidService.update(bid),HttpStatus.OK);
    }
}
