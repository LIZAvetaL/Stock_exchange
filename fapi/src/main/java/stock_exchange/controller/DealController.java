package stock_exchange.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stock_exchange.model.Deal;
import stock_exchange.model.response.PageResponse;
import stock_exchange.service.DealService;

@RestController
@RequestMapping("/deal")
public class DealController {
    private final DealService dealService;

    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @GetMapping("/find")
    public ResponseEntity<PageResponse<Deal>> find(@RequestParam int page,
                                                   @RequestParam int size,
                                                   @RequestParam String[] sort,
                                                   @RequestParam(name = "broker-id") int brokerId) {
        return new ResponseEntity(dealService.find(page, size, sort, brokerId), HttpStatus.OK);
    }
}
