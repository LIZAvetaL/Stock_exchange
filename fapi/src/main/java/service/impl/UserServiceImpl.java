package service.impl;

import config.UrlConstants;
import model.User;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final RestTemplate restTemplate;

    @Autowired
    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public User findByUserName(String name) {
        return restTemplate.getForObject(UrlConstants.UserUrl + "find/name/" + name, User.class);
    }

    @Override
    public List<User> findAll() {
        return restTemplate.getForObject(UrlConstants.UserUrl, List.class);
    }

    @Override
    public void save(User user) {
        return restTemplate.postForEntity(UrlConstants.UserUrl + "create/", user, String.class);
    }

}
