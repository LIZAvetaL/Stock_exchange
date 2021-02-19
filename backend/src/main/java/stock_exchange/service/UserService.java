package stock_exchange.service;

import org.springframework.data.domain.Page;
import stock_exchange.dto.CreateBrokerDTO;
import stock_exchange.dto.CreateUserDTO;
import stock_exchange.dto.UserDTO;
import stock_exchange.model.User;
import stock_exchange.response.MessageResponse;

import java.util.List;

public interface UserService {

    UserDTO findById(int id);

    User findUser(int id);

    User findUser(String name);

    Page<UserDTO> findAll(int page, int size, String[] sort);

    void save(UserDTO user);

    UserDTO findByEmail(String email);

    boolean existsByEmail(String email);

    void register(CreateUserDTO user);

    List<UserDTO> findClients();

    MessageResponse update(int id, String role);

    void registerBroker(CreateBrokerDTO broker);

    void changeRole(User user, String role_broker);

    MessageResponse block(int userId);

    MessageResponse unblock(int userId);
}
