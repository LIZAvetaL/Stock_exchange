package stock_exchange.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import stock_exchange.config.UrlConstants;
import stock_exchange.model.Deal;
import stock_exchange.model.response.PageResponse;
import stock_exchange.service.DealService;

import java.util.List;

@Service
public class DealServiceImpl implements DealService {
    private final RestTemplate restTemplate;

    @Autowired
    public DealServiceImpl(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    @Override
    public PageResponse<Deal> find(int page, int size, String[] sort, int brokerId) {
        return restTemplate.getForObject(
                UrlConstants.DealUrl + "find"
                        + "?page=" + page + "&size=" + size + "&sort=" + String.join(",", sort)
                        + "&brokerId=" + brokerId, PageResponse.class);
    }
}
