package stock_exchange.service;

import stock_exchange.dto.CreateUserDTO;
import stock_exchange.dto.UserDTO;
import stock_exchange.model.User;
import stock_exchange.response.MessageResponse;

import java.util.List;

public interface UserService {

    UserDTO findById(int id);

    User findUser(int id);

    User findUser(String name);

    List<UserDTO> findAll();

    void save(UserDTO user);

    UserDTO findByEmail(String email);

    boolean existsByEmail(String email);

    void register(CreateUserDTO user);

    List<UserDTO> findClients();

    MessageResponse update(int id, String role);
}
