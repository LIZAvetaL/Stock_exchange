package stock_exchange.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import stock_exchange.model.User;
import stock_exchange.model.response.MessageResponse;
import stock_exchange.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity findAll() {
        return new ResponseEntity(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find/id/{userId}")
    public ResponseEntity find(@PathVariable(name = "userId") int userId) {
        return new ResponseEntity(userService.find(userId), HttpStatus.OK);
    }

    @GetMapping("/clients")
    public ResponseEntity findClient() {
        return new ResponseEntity(userService.findClient(), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<MessageResponse> update(@RequestParam(name = "id") int userId,
                                                  @RequestParam String role) {
        return new ResponseEntity(userService.update(userId, role), HttpStatus.OK);
    }

}
