package stock_exchange.service.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import stock_exchange.config.StatusConst;
import stock_exchange.dto.CreateBrokerDTO;
import stock_exchange.dto.CreateUserDTO;
import stock_exchange.exception.NotFoundException;
import stock_exchange.model.User;
import stock_exchange.repository.UserRepository;
import stock_exchange.dto.UserDTO;
import stock_exchange.response.MessageResponse;
import stock_exchange.service.BrokerService;
import stock_exchange.service.RoleService;
import stock_exchange.service.StatusService;
import stock_exchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BrokerService brokerService;
    private final StatusService statusService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService,
                           @Lazy BrokerService brokerService,
                           StatusService statusService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.brokerService = brokerService;
        this.statusService = statusService;
    }

    @Override
    public UserDTO findById(int id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User hasn't been found"));
        return transfer(user);
    }

    @Override
    public Page<UserDTO> findAll(int page, int size, String[] sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortType(sort)));

        return userRepository.findAll(pageable).map(this::transfer);


    }

    @Override
    public void save(UserDTO userDTO) {
        User user = new User();
        userRepository.save(user);
    }

    @Override
    public UserDTO findByEmail(String email) {
        return transfer(userRepository.findUserByEmail(email).orElseThrow(
                () -> new NotFoundException("User hasn't been found")
        ));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public void register(CreateUserDTO userDTO) {
        User user = transfer(userDTO);
        userRepository.save(user);
    }

    @Override
    public List<UserDTO> findClients() {
        return null;
    }

    @Override
    public MessageResponse update(int userId, String roleName) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User hasn't been found")
        );
        user.setRole(roleService.findRole(roleName));
        userRepository.save(user);
        return new MessageResponse("Role has been changed");
    }

    @Override
    public void registerBroker(CreateBrokerDTO brokerDTO) {
        User user = transfer(brokerDTO);
        userRepository.save(user);
        brokerService.save(findUser(user.getName()), brokerDTO.getExchange());
    }

    @Override
    public void changeRole(User user, String role) {
        user.setRole(roleService.findRole(role));
        userRepository.save(user);
    }

    @Override
    public MessageResponse block(int userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User hasn't been found")
        );
        user.setStatus(statusService.find(StatusConst.BLOCK.getName()));
        userRepository.save(user);
        return new MessageResponse("User is blocked");
    }

    @Override
    public MessageResponse unblock(int userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User hasn't been found")
        );
        user.setStatus(statusService.find(StatusConst.UNBLOCK.getName()));
        userRepository.save(user);
        return new MessageResponse("User is unblocked");
    }

    @Override
    public boolean existsByName(String name) {
        return userRepository.existsUserByName(name);
    }

    public User findUser(int id) {
        return userRepository.findById(id).get();
    }

    public User findUser(String name) {
        return userRepository.findUserByName(name);
    }

    private User transfer(UserDTO userDTO) {
        return new User(userDTO.getEmail(), userDTO.getPassword(), userDTO.getName(),
                roleService.findRole(userDTO.getRole()), statusService.find(userDTO.getStatus()));
    }

    private User transfer(CreateUserDTO userDTO) {
        return new User(userDTO.getEmail(), userDTO.getPassword(), userDTO.getName(),
                roleService.findRole(userDTO.getRole()), statusService.find(StatusConst.UNBLOCK.getName()));
    }

    private User transfer(CreateBrokerDTO userDTO) {
        return new User(userDTO.getEmail(), userDTO.getPassword(), userDTO.getName(),
                roleService.findRole(userDTO.getRole()), statusService.find(StatusConst.UNBLOCK.getName()));
    }

    private UserDTO transfer(User user) {
        return new UserDTO(user.getId(), user.getEmail(), user.getPassword(),
                user.getName(), user.getRole().getRoleName(), user.getStatus().getStatusName());
    }

    private List<Sort.Order> sortType(String[] sort) {
        List<Sort.Order> list = new ArrayList<>();
        for (int i = 0; i< sort.length;i++ ) {
            list.add(new Sort.Order(Sort.Direction.fromString(sort[i]), sort[++i]));
        }
        return list;
    }
}
