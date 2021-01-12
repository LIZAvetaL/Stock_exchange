package stock_exchange.service;

import stock_exchange.dto.UserDTO;
import stock_exchange.model.User;

import java.util.List;

public interface UserService {

    UserDTO findById(int id);
    List<UserDTO> findAll();
    void save(UserDTO user);

    UserDTO findByName(String name);
}
