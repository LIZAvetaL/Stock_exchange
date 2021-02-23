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
import stock_exchange.model.response.PageResponse;
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
    public ResponseEntity<PageResponse<User>> findAll(@RequestParam int page,
                                                      @RequestParam int size,
                                                      @RequestParam String[] sort) {
        return new ResponseEntity(userService.findAll(page, size, sort), HttpStatus.OK);
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

    @PostMapping("/block")
    public ResponseEntity<MessageResponse> block(@RequestParam(name = "id") int userId) {
        return new ResponseEntity(userService.block(userId), HttpStatus.OK);
    }

    @PostMapping("/unblock")
    public ResponseEntity<MessageResponse> unblock(@RequestParam(name = "id") int userId) {
        return new ResponseEntity(userService.unblock(userId), HttpStatus.OK);
    }

}
