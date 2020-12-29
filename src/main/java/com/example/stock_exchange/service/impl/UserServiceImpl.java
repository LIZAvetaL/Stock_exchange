package com.example.stock_exchange.service.impl;

import com.example.stock_exchange.model.User;
import com.example.stock_exchange.repository.RoleRepository;
import com.example.stock_exchange.repository.UserRepository;
import com.example.stock_exchange.dto.UserDTO;
import com.example.stock_exchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDTO findById(int id) {
        User user = userRepository.getOne(id);
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
        User user = new User( userDTO.getEmail(), userDTO.getPassword(),
                userDTO.getName(), roleRepository.findRoleByRoleName(userDTO.getRole()));
        userRepository.save(user);
    }
}
