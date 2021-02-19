package stock_exchange.service.impl;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.ResponseErrorHandler;
import stock_exchange.config.UrlConstants;
import stock_exchange.exception.RestTemplateResponseErrorHandler;
import stock_exchange.model.CreateBroker;
import stock_exchange.model.CreateUser;
import stock_exchange.model.User;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stock_exchange.model.response.MessageResponse;
import stock_exchange.model.response.PageResponse;
import stock_exchange.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final RestTemplate restTemplate;

    @Autowired
    public UserServiceImpl(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    @Override
    public User findByName(String name) {
        return restTemplate.getForObject(UrlConstants.UserUrl + "find/" + name, User.class);
    }

    @Override
    public PageResponse<User> findAll(int page, int size, String[] sort) {
        return restTemplate.getForObject(UrlConstants.UserUrl + "?page=" + page + "&size=" + size
                + "&sort=" + String.join(",", sort), PageResponse.class);
    }

    @Override
    public User findByEmail(String email) {
        return restTemplate.getForObject(UrlConstants.UserUrl + "find/name/" + email, User.class);
    }

    @Override
    public boolean existsByEmail(String email) {
        return restTemplate.getForObject(UrlConstants.UserUrl + "exist/" + email, Boolean.class);
    }

    @Override
    public void register(CreateUser user) {
        restTemplate.postForEntity(UrlConstants.UserUrl + "/registration", user, String.class);
    }

    @Override
    public List<User> findClient() {
        return restTemplate.getForObject(UrlConstants.UserUrl + "clients/", List.class);
    }

    @Override
    public User find(int userId) {
        return restTemplate.getForObject(UrlConstants.UserUrl + "find/id/" + userId, User.class);
    }

    @Override
    public MessageResponse update(int userId, String role) {
        return restTemplate.postForEntity(UrlConstants.UserUrl + "update" + "?id=" + userId + "&role=" + role,
                null, MessageResponse.class).getBody();
    }

    @Override
    public void registerBroker(CreateBroker broker) {
        restTemplate.postForEntity(UrlConstants.UserUrl + "/registration/broker", broker, String.class);
    }

    @Override
    public MessageResponse block(int userId) {
        return restTemplate.postForEntity(UrlConstants.UserUrl + "block" + "?id=" + userId,
                null, MessageResponse.class).getBody();
    }

    @Override
    public MessageResponse unblock(int userId) {
        return restTemplate.postForEntity(UrlConstants.UserUrl + "unblock" + "?id=" + userId,
                null, MessageResponse.class).getBody();
    }


}
