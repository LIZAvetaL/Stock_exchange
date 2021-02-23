package stock_exchange.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import stock_exchange.config.UrlConstants;
import stock_exchange.model.CreateStockExchange;
import stock_exchange.model.OwnerStatistics;
import stock_exchange.model.StockExchange;
import stock_exchange.model.response.MessageResponse;
import stock_exchange.model.response.PageResponse;
import stock_exchange.service.ExchangeService;

import java.util.List;

@Service
public class ExchangeServiceImpl implements ExchangeService {
    private final RestTemplate restTemplate;

    @Autowired
    public ExchangeServiceImpl(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    @Override
    public PageResponse<StockExchange> findAll(int page, int size, String[] sort, int ownerId) {
         return restTemplate.getForObject(UrlConstants.ExchangeUrl+ "?page=" + page + "&size=" + size
                + "&sort=" + String.join(",", sort)+"&ownerId=" + ownerId, PageResponse.class);
    }

    @Override
    public List<StockExchange> findAll() {
        return restTemplate.getForObject(UrlConstants.ExchangeUrl+"find/all", List.class);
    }

    @Override
    public StockExchange find(int exchangeId) {
        return restTemplate.getForObject(UrlConstants.ExchangeUrl + "find/" + exchangeId, StockExchange.class);
    }

    @Override
    public MessageResponse changeStatus(int exchangeId, String status) {
        return restTemplate.postForEntity(UrlConstants.ExchangeUrl + "change-status?id="
                + exchangeId + "&status=" + status, null, MessageResponse.class).getBody();
    }

    @Override
    public MessageResponse update(StockExchange exchange) {
        return restTemplate.postForEntity(UrlConstants.ExchangeUrl + "update", exchange,
                MessageResponse.class).getBody();
    }

    @Override
    public MessageResponse create(int ownerId, CreateStockExchange exchange) {
        return restTemplate.postForEntity(UrlConstants.ExchangeUrl + "create?owner-id="+ownerId, exchange,
                MessageResponse.class).getBody();
    }

    @Override
    public PageResponse<OwnerStatistics> getStatistics(int exchangeId, int page, int size) {
        return restTemplate.getForObject(UrlConstants.ExchangeUrl+ "find/statistics?page=" + page + "&size=" + size
            + "&exchangeId=" + exchangeId, PageResponse.class);
    }
}
