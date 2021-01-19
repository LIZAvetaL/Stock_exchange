package stock_exchange.service;

import org.springframework.http.ResponseEntity;
import stock_exchange.model.request.LoginRequest;
import stock_exchange.model.request.SignupRequest;
import stock_exchange.model.response.JwtResponse;

public interface AuthService {
    JwtResponse authenticateUser(LoginRequest loginRequest);

    ResponseEntity registerUser(SignupRequest signUpRequest);
}
