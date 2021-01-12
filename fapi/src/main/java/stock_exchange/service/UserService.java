package stock_exchange.service;
import stock_exchange.model.User;

import java.util.List;

public interface UserService {

    User findByName(String name);
    List<User> findAll();
}