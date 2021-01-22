package stock_exchange.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import stock_exchange.dto.BidDTO;
import stock_exchange.model.Deal;
import stock_exchange.service.BrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/broker")
public class BrokerController {
    private final BrokerService brokerService;

    @Autowired
    public BrokerController(BrokerService brokerService) {
        this.brokerService = brokerService;
    }

    @GetMapping("find/unemployed")
    public ResponseEntity<Map<String, Object>> findAll(@RequestParam(required = false) String title,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "name") String sort) {

        return new ResponseEntity<>(brokerService.findAllUnemployed(title, page, size, sort), HttpStatus.OK);
    }


    @GetMapping("/bids/{id}")
    public List<BidDTO> findAll(@PathVariable(name = "id") int id) {
        return brokerService.findBrokersBids(id);
    }

    @GetMapping("/find")
    public ResponseEntity find(@RequestParam int id) {

        return new ResponseEntity(brokerService.findBroker(id), HttpStatus.OK);
    }

    @PostMapping("/create-deal/{seller-bid-id}/{buyer-bid-id}")
    public Deal employBroker(@PathVariable(name = "seller-bid-id") int sellerBidId,
                             @PathVariable(name = "buyer-bid-id") int buyerBidId) {
        return brokerService.createDeal(sellerBidId, buyerBidId);
    }
}
