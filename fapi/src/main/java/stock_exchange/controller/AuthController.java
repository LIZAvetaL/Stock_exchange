package stock_exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stock_exchange.model.payload.request.LoginRequest;
import stock_exchange.model.payload.request.SignupRequest;
import stock_exchange.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity authenticateUser(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity(authService.authenticateUser(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        return new ResponseEntity(authService.registerUser(signUpRequest), HttpStatus.OK);
    }
}
