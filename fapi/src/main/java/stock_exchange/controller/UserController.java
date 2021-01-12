package stock_exchange.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import stock_exchange.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity findAll() {

        return new ResponseEntity(userService.findByName("123"), HttpStatus.OK);
    }


}
