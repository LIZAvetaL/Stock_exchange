package stock_exchange.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import stock_exchange.config.UrlConstants;
import stock_exchange.model.Broker;
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
    public BrokerServiceImpl(RestTemplateBuilder restTemplate) {

        this.restTemplate = restTemplate.build();
    }

    @Override
    public PageResponse<UnemployedBroker> findAllUnemployed(String title, int page, int size, String sort) {
        return restTemplate.getForObject(
                UrlConstants.BrokerUrl + "find/unemployed" + "?title=" + title
                        + "&page=" + page + "&size=" + size + "&sort=" + sort,
                PageResponse.class);
    }

    @Override
    public List<Broker> findBrokers(int clientId) {
        return restTemplate.getForObject(
                UrlConstants.BrokerUrl + "find" + "?client-id=" + clientId, List.class);
    }

    @Override
    public MessageResponse employ(int brokerId, int clientId) {
        return restTemplate.postForEntity(UrlConstants.BrokerUrl + "employ" + "?broker-id=" + brokerId
                + "&client-id=" + clientId, null, MessageResponse.class).getBody();
    }
}
