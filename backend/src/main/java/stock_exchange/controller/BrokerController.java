package stock_exchange.controller;

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

@RestController
@RequestMapping("/broker")
public class BrokerController {
    private final BrokerService brokerService;

    @Autowired
    public BrokerController(BrokerService brokerService) {
        this.brokerService = brokerService;
    }

    @GetMapping("/bids/{id}")
    public List<BidDTO> findAll(@PathVariable(name = "id") int id) {
        return brokerService.findBrokersBids(id);
    }

    @PostMapping("/create-deal/{seller-bid-id}/{buyer-bid-id}")
    public Deal employBroker(@PathVariable(name = "seller-bid-id") int sellerBidId,
                             @PathVariable(name = "buyer-bid-id") int buyerBidId){
        return brokerService.createDeal(sellerBidId, buyerBidId);
    }
}
