package stock_exchange.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stock_exchange.dto.DealDTO;
import stock_exchange.service.DealService;

@RestController
@RequestMapping("/deal")
public class DealController {

    private final DealService dealService;

    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @GetMapping("/find")
    public ResponseEntity<Page<DealDTO>> find(@RequestParam int page,
                                              @RequestParam int size,
                                              @RequestParam String[] sort,
                                              @RequestParam int brokerId) {
        return new ResponseEntity(dealService.find(page, size, sort, brokerId), HttpStatus.OK);
    }
}
