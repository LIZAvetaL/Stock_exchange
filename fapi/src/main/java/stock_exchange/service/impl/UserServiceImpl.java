package stock_exchange.service.impl;

import stock_exchange.config.UrlConstants;
import stock_exchange.model.User;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public List<User> findAll() {
        return restTemplate.getForObject(UrlConstants.UserUrl, List.class);
    }


}
