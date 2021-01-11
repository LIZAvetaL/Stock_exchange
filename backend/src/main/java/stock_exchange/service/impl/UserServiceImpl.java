package stock_exchange.service.impl;

import stock_exchange.model.User;
import stock_exchange.repository.UserRepository;
import stock_exchange.dto.UserDTO;
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
        User user= userRepository.findById(id).get();
        return new UserDTO(user.getId(), user.getEmail(), user.getPassword(),
                user.getName(), user.getRole().getRoleName());
    }
    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO(user.getId(), user.getEmail(), user.getPassword(),
                    user.getName(), user.getRole().getRoleName());
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @Override
    public void save(UserDTO userDTO) {
        User user = new User();
        userRepository.save(user);
    }

}
