package stock_exchange.service;

import stock_exchange.model.CreateBroker;
import stock_exchange.model.CreateUser;
import stock_exchange.model.User;
import stock_exchange.model.response.MessageResponse;
import stock_exchange.model.response.PageResponse;

import java.util.List;

public interface UserService {

    User findByName(String name);

    PageResponse<User> findAll(int page, int size, String[] sort);

    User findByEmail(String email);

    boolean existsByEmail(String email);

    void register(CreateUser user);

    List<User> findClient();

    User find(int userId);

    MessageResponse update(int userId, String role);

    void registerBroker(CreateBroker broker);

    MessageResponse block(int userId);

    MessageResponse unblock(int userId);

    boolean existsByName(String name);
}
