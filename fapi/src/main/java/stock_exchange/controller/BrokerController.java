package stock_exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stock_exchange.model.Broker;
import stock_exchange.model.response.PageResponse;
import stock_exchange.service.BrokerService;

import java.util.List;

@RestController
@RequestMapping("/broker")
public class BrokerController {
    private final BrokerService brokerService;

    @Autowired
    public BrokerController(BrokerService brokerService) {
        this.brokerService = brokerService;
    }

    @GetMapping("/find/unemployed")
    public ResponseEntity findAll(@RequestParam(required = false) String title,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "name") String sort) {
        PageResponse<Broker> brokers=brokerService.findAllUnemployed(title, page, size, sort);

        return new ResponseEntity(brokers, HttpStatus.OK);
    }
    @GetMapping("/find")
    public ResponseEntity findAll(@RequestParam(name = "client-id") int clientId) {
        List<Broker> brokers=brokerService.findBrokers(clientId);

        return new ResponseEntity(brokers, HttpStatus.OK);
    }
}
