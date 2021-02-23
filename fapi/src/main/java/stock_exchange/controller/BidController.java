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
import stock_exchange.model.Deal;
import stock_exchange.model.request.CreateBid;
import stock_exchange.model.response.MessageResponse;
import stock_exchange.model.response.PageResponse;
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
    public ResponseEntity<Bid> find(@RequestParam int id) {
        return new ResponseEntity(bidService.find(id), HttpStatus.OK);
    }

    @GetMapping("/find/all")
    public ResponseEntity<PageResponse<Bid>> findAll(@RequestParam String issuer,
                                                     @RequestParam int page,
                                                     @RequestParam int size,
                                                     @RequestParam String[] sort) {
        return new ResponseEntity(bidService.findAll(issuer, page, size, sort), HttpStatus.OK);
    }

    @GetMapping("/find/brokers-bids")
    public ResponseEntity findBrokersBids(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam String[] sort,
                                          @RequestParam int brokerId) {
        return new ResponseEntity(bidService.findBrokersBids(page, size, sort, brokerId), HttpStatus.OK);
    }

    @GetMapping("/find/clients-bids")
    public ResponseEntity findClientsBids(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam String[] sort,
                                          @RequestParam int clientId) {
        return new ResponseEntity(bidService.findClientsBids(page, size, sort, clientId), HttpStatus.OK);
    }

    @GetMapping("/find/bids-for-deal")
    public ResponseEntity findBids(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam String[] sort,
                                   @RequestParam int bidId) {
        return new ResponseEntity(bidService.findBids(page, size, sort, bidId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity createBid(@RequestParam int id,
                                    @RequestBody CreateBid createBid) {

        return new ResponseEntity(bidService.create(id, createBid), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<MessageResponse> update(@RequestBody Bid bid) {

        return new ResponseEntity(bidService.update(bid), HttpStatus.OK);
    }

    @PostMapping("/create-deal/{seller-bid-id}/{buyer-bid-id}/{price}")
    public ResponseEntity<Integer> create(@PathVariable(name = "seller-bid-id") int sellerBidId,
                                                  @PathVariable(name = "buyer-bid-id") int buyerBidId,
                                                  @PathVariable double price) {
        return new ResponseEntity(bidService.createDeal(sellerBidId, buyerBidId, price), HttpStatus.OK);
    }
}
