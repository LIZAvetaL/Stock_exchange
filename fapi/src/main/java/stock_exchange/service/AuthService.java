package stock_exchange.service;

import org.springframework.http.ResponseEntity;
import stock_exchange.model.payload.request.LoginRequest;
import stock_exchange.model.payload.request.SignupRequest;

public interface AuthService {
    ResponseEntity authenticateUser(LoginRequest loginRequest);

    ResponseEntity registerUser(SignupRequest signUpRequest);
}
