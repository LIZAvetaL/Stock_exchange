package stock_exchange.service;

import stock_exchange.dto.UserDTO;
import stock_exchange.model.User;

import java.util.List;

public interface UserService {

    UserDTO findById(int id);

    User findUser(int id);

    List<UserDTO> findAll();

    void save(UserDTO user);

    UserDTO findByEmail(String email);

    boolean existsByEmail(String email);

    void register(UserDTO user);

    List<UserDTO> findClients();
}
