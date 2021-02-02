package stock_exchange.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import stock_exchange.config.UrlConstants;
import stock_exchange.model.Bid;
import stock_exchange.model.request.CreateBid;
import stock_exchange.model.response.MessageResponse;
import stock_exchange.model.response.PageResponse;
import stock_exchange.service.BidService;

import java.util.Arrays;
import java.util.Map;


@Service
public class BidServiceImpl implements BidService {

    private final RestTemplate restTemplate;

    @Autowired
    public BidServiceImpl(RestTemplateBuilder restTemplate) {

        this.restTemplate = restTemplate.build();
    }

    @Override
    public PageResponse<Bid> findBrokersBids(int page, int size, int brokerId) {
        return restTemplate.getForObject(
                UrlConstants.BidUrl + "find/brokers-bids"
                        + "?page=" + page + "&size=" + size + "&brokerId=" + brokerId, PageResponse.class);
    }

    @Override
    public PageResponse<Bid> findClientsBids(int page, int size, String[] sort, int clientId) {
        return restTemplate.getForObject(
                UrlConstants.BidUrl + "find/clients-bids"
                        + "?page=" + page + "&size=" + size + "&sort=" + String.join(",", sort)
                        + "&clientId=" + clientId, PageResponse.class);
    }

    @Override
    public void create(int id, CreateBid createBid) {
        restTemplate.postForEntity(UrlConstants.BidUrl + "create/?id=" + id, createBid, String.class);
    }

    @Override
    public MessageResponse update(Bid bid) {
        return restTemplate.postForEntity(UrlConstants.BidUrl + "update", bid, MessageResponse.class).getBody();
    }

    @Override
    public Bid find(int id) {
        return restTemplate.getForObject(UrlConstants.BidUrl + "find" + "?id=" + id, Bid.class);
    }
}
