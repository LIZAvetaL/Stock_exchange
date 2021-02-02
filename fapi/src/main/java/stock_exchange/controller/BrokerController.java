package stock_exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stock_exchange.model.Broker;
import stock_exchange.model.UnemployedBroker;
import stock_exchange.model.response.MessageResponse;
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
    public ResponseEntity<UnemployedBroker> findAll(@RequestParam(required = false) String title,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size,
                                                    @RequestParam(defaultValue = "name") String sort) {
        PageResponse<UnemployedBroker> brokers = brokerService.findAllUnemployed(title, page, size, sort);

        return new ResponseEntity(brokers, HttpStatus.OK);
    }

    @GetMapping("/find/all")
    public ResponseEntity<PageResponse<Broker>> findAll(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size,
                                                        @RequestParam(name = "client-id") int clientId) {

        return new ResponseEntity(brokerService.findBrokers(page, size, clientId), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity find(@RequestParam(name = "client-id") int clientId) {
        List<Broker> brokers = brokerService.findBrokers(clientId);
        return new ResponseEntity(brokers, HttpStatus.OK);
    }

    @PostMapping("/employ/{broker-id}/{client-id}")
    public ResponseEntity<MessageResponse> employ(@PathVariable(name = "broker-id") int brokerId,
                                                  @PathVariable(name = "client-id") int clientId) {
        return new ResponseEntity(brokerService.employ(brokerId, clientId), HttpStatus.OK);
    }

    @PostMapping("/dismiss/{broker-id}")
    public ResponseEntity<MessageResponse> dismiss(@PathVariable(name = "broker-id") int brokerId) {
        return new ResponseEntity(brokerService.dismiss(brokerId), HttpStatus.OK);
    }
}
