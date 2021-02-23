package stock_exchange.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import stock_exchange.dto.BidDTO;
import stock_exchange.dto.BrokerDTO;
import stock_exchange.dto.BrokerStatisticsDTO;
import stock_exchange.dto.StockExchangeDTO;
import stock_exchange.dto.UnemployedBrokerDTO;
import stock_exchange.model.Broker;
import stock_exchange.model.Deal;
import stock_exchange.model.StockExchange;
import stock_exchange.response.MessageResponse;
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
    public ResponseEntity<List<UnemployedBrokerDTO>> findAll(@RequestParam(required = false) String title,
                                                             @RequestParam int page,
                                                             @RequestParam int size,
                                                             @RequestParam String[] sort) {

        return new ResponseEntity(brokerService.findAllUnemployed(title, page, size, sort), HttpStatus.OK);
    }

    @GetMapping("/find/all")
    public ResponseEntity findAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(name = "client-id") int clientId) {
        Page<BrokerDTO> brokers = brokerService.findBrokers(page, size, clientId);

        return new ResponseEntity(brokers, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity findAll(@RequestParam(name = "client-id") int clientId) {
        List<BrokerDTO> brokers = brokerService.findBrokers(clientId);
        return new ResponseEntity(brokers, HttpStatus.OK);
    }

    @GetMapping("/find/id")
    public ResponseEntity find(@RequestParam int id) {

        return new ResponseEntity(brokerService.findBroker(id), HttpStatus.OK);
    }

    @PostMapping("/employ")
    public ResponseEntity<MessageResponse> employ(@RequestParam(name = "broker-id") int brokerId,
                                                  @RequestParam(name = "client-id") int clientId) {
        return new ResponseEntity(brokerService.employ(brokerId, clientId), HttpStatus.OK);
    }

    @PostMapping("/dismiss")
    public ResponseEntity<MessageResponse> dismiss(@RequestParam(name = "broker-id") int brokerId) {
        return new ResponseEntity(brokerService.dismiss(brokerId), HttpStatus.OK);
    }

    @GetMapping("/find/statistics")
    public ResponseEntity<List<BrokerStatisticsDTO>> getStatistics(@RequestParam int page,
                                                                   @RequestParam int size,
                                                                   @RequestParam int clientId) {

        Page<BrokerStatisticsDTO> brokerStatisticsDTOS = brokerService.getStatistics(page, size, clientId);
        return new ResponseEntity(brokerStatisticsDTOS, HttpStatus.OK);
    }

    @PostMapping("/update/{broker-id}/{exchange}")
    public ResponseEntity<MessageResponse> update(@PathVariable(name = "broker-id") int brokerId,
                                                  @PathVariable String exchange) {
        return new ResponseEntity(brokerService.update(brokerId, exchange), HttpStatus.OK);
    }
}
