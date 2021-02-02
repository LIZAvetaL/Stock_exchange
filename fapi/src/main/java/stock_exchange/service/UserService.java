package stock_exchange.service;

import stock_exchange.model.CreateUser;
import stock_exchange.model.User;
import stock_exchange.model.response.MessageResponse;

import java.util.List;

public interface UserService {

    User findByName(String name);

    List<User> findAll();

    User findByEmail(String email);

    boolean existsByEmail(String email);

    void register(CreateUser user);

    List<User> findClient();

    User find(int userId);

    MessageResponse update(int userId, String role);
}
