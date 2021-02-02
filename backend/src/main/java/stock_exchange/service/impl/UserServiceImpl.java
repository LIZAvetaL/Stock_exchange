package stock_exchange.service.impl;

import stock_exchange.dto.CreateUserDTO;
import stock_exchange.exception.NotFoundException;
import stock_exchange.model.User;
import stock_exchange.repository.UserRepository;
import stock_exchange.dto.UserDTO;
import stock_exchange.response.MessageResponse;
import stock_exchange.service.RoleService;
import stock_exchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public UserDTO findById(int id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("user error"));
        return transfer(user);
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = transfer(user);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @Override
    public void save(UserDTO userDTO) {
        User user = new User();
        userRepository.save(user);
    }

    @Override
    public UserDTO findByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return transfer(user);
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
        User user=userRepository.findById(userId).get();
        user.setRole(roleService.findRole(roleName));
        userRepository.save(user);
        return new MessageResponse("<3");
    }

    public User findUser(int id) {
        return userRepository.findById(id).get();
    }

    public User findUser(String name) {
        return userRepository.findUserByName(name);
    }

    private User transfer(UserDTO userDTO) {
        return new User(userDTO.getEmail(), userDTO.getPassword(), userDTO.getName(),
                roleService.findRole(userDTO.getRole()));
    }

    private User transfer(CreateUserDTO userDTO) {
        return new User(userDTO.getEmail(), userDTO.getPassword(), userDTO.getName(),
                roleService.findRole(userDTO.getRole()));
    }

    private UserDTO transfer(User user) {
        return new UserDTO(user.getId(), user.getEmail(), user.getPassword(),
                user.getName(), user.getRole().getRoleName());
    }

}
