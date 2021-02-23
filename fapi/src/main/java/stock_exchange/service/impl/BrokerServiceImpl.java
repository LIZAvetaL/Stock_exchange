package stock_exchange.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import stock_exchange.config.UrlConstants;
import stock_exchange.model.Broker;
import stock_exchange.model.BrokerStatisticsDTO;
import stock_exchange.model.StockExchange;
import stock_exchange.model.UnemployedBroker;
import stock_exchange.model.response.MessageResponse;
import stock_exchange.model.response.PageResponse;
import stock_exchange.service.BrokerService;

import java.util.List;
import java.util.Map;

@Service
public class BrokerServiceImpl implements BrokerService {
    private final RestTemplate restTemplate;

    @Autowired
    public BrokerServiceImpl(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    @Override
    public PageResponse<UnemployedBroker> findAllUnemployed(String title, int page, int size, String[] sort) {
        return restTemplate.getForObject(
                UrlConstants.BrokerUrl + "find/unemployed" + "?title=" + title
                        + "&page=" + page + "&size=" + size + "&sort=" + String.join(",", sort),
                PageResponse.class);
    }

    @Override
    public PageResponse<Broker> findBrokers(int page, int size, int clientId) {
        return restTemplate.getForObject(
                UrlConstants.BrokerUrl + "find/all" + "?page=" + page + "&size=" + size + "&client-id=" + clientId,
                PageResponse.class);
    }

    @Override
    public List<Broker> findBrokers(int clientId) {
        return restTemplate.getForObject(
                UrlConstants.BrokerUrl + "find" + "?client-id=" + clientId,
                List.class);
    }

    @Override
    public MessageResponse employ(int brokerId, int clientId) {
        return restTemplate.postForEntity(UrlConstants.BrokerUrl + "employ" + "?broker-id=" + brokerId
                + "&client-id=" + clientId, null, MessageResponse.class).getBody();
    }

    @Override
    public MessageResponse dismiss(int brokerId) {
        return restTemplate.postForEntity(UrlConstants.BrokerUrl + "dismiss" + "?broker-id=" + brokerId,
                null, MessageResponse.class).getBody();
    }

    @Override
    public PageResponse<BrokerStatisticsDTO> getStatistics(int page, int size, int clientId) {
        return restTemplate.getForObject(UrlConstants.BrokerUrl + "find/statistics"
                + "?page=" + page + "&size=" + size + "&clientId=" + clientId, PageResponse.class);
    }

    @Override
    public MessageResponse update(int brokerId, String exchange) {
        return restTemplate.postForEntity(UrlConstants.BrokerUrl + "update/" + brokerId + "/" + exchange,
                null, MessageResponse.class).getBody();
    }
}
