package stock_exchange.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import stock_exchange.config.UrlConstants;
import stock_exchange.model.Bid;
import stock_exchange.model.BrokerBid;
import stock_exchange.model.Deal;
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
    public BidServiceImpl(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    @Override
    public PageResponse<Bid> findBrokersBids(int page, int size, String[] sort, int brokerId) {
        return restTemplate.getForObject(
                UrlConstants.BidUrl + "find/brokers-bids"
                        + "?page=" + page + "&size=" + size + "&sort=" + String.join(",", sort)
                        + "&brokerId=" + brokerId, PageResponse.class);
    }

    @Override
    public PageResponse<Bid> findClientsBids(int page, int size, String[] sort, int clientId) {
        return restTemplate.getForObject(
                UrlConstants.BidUrl + "find/clients-bids"
                        + "?page=" + page + "&size=" + size + "&sort=" + String.join(",", sort)
                        + "&clientId=" + clientId, PageResponse.class);
    }

    @Override
    public MessageResponse create(int id, CreateBid createBid) {
        return restTemplate.postForEntity(UrlConstants.BidUrl + "create?id=" + id, createBid, MessageResponse.class).getBody();
    }

    @Override
    public MessageResponse update(Bid bid) {
        return restTemplate.postForEntity(UrlConstants.BidUrl + "update", bid, MessageResponse.class).getBody();
    }

    @Override
    public Bid find(int id) {
        return restTemplate.getForObject(UrlConstants.BidUrl + "find" + "?id=" + id, Bid.class);
    }

    @Override
    public PageResponse<BrokerBid> findBids(int page, int size, String[] sort, int bidId) {
        return restTemplate.getForObject(
                UrlConstants.BidUrl + "find/bids-for-deal"
                        + "?page=" + page + "&size=" + size + "&sort=" + String.join(",", sort)
                        + "&bidId=" + bidId, PageResponse.class);
    }

    @Override
    public MessageResponse createDeal(int sellerBidId, int buyerBidId, double price) {
        return restTemplate.postForEntity(UrlConstants.BidUrl + "create-deal/" + sellerBidId + "/" + buyerBidId
                + "/" + price, null, MessageResponse.class).getBody();
    }

    @Override
    public PageResponse<Bid> findAll(String issuer, int page, int size, String[] sort) {
        return restTemplate.getForObject(
                UrlConstants.BidUrl + "find/all"
                        + "?issuer=" + issuer + "&page=" + page + "&size=" + size + "&sort=" + String.join(",", sort)
                , PageResponse.class);
    }
}
