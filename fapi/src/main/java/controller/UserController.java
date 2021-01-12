package controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import stock_exchange.dto.UserDTO;
import stock_exchange.model.User;
import stock_exchange.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<UserDTO> findAll() {

        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDTO findUser(@PathVariable(name = "id") int id) {
        return userService.findById(id);
    }

    @PostMapping("/create")
    public void create(@RequestBody UserDTO user) {
        userService.save(user);
    }

}
