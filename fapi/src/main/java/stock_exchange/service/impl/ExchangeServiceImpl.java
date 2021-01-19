package stock_exchange.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import stock_exchange.config.UrlConstants;
import stock_exchange.model.StockExchange;
import stock_exchange.service.ExchangeService;

import java.util.List;

@Service
public class ExchangeServiceImpl implements ExchangeService {
    private final RestTemplate restTemplate;

    @Autowired
    public ExchangeServiceImpl(RestTemplateBuilder restTemplate) {

        this.restTemplate = restTemplate.build();
    }

    @Override
    public List<StockExchange> findAll(int ownerId) {
        return restTemplate.getForObject(UrlConstants.ExchangeUrl + ownerId, List.class);
    }

    @Override
    public StockExchange find(int exchangeId) {
        return restTemplate.getForObject(UrlConstants.ExchangeUrl + exchangeId, StockExchange.class);
    }
}
