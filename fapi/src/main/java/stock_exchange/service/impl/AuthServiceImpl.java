package stock_exchange.service.impl;

import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import stock_exchange.exception.NotFoundException;
import stock_exchange.model.CreateBroker;
import stock_exchange.model.CreateUser;
import stock_exchange.model.User;
import stock_exchange.model.request.LoginRequest;
import stock_exchange.model.request.SignupRequest;
import stock_exchange.model.response.JwtResponse;
import stock_exchange.model.response.MessageResponse;
import stock_exchange.security.jwt.JwtUtils;
import stock_exchange.security.services.UserDetailsImpl;
import stock_exchange.service.AuthService;
import stock_exchange.service.EmailSender;
import stock_exchange.service.UserService;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private PasswordEncoder encoder;
    private JwtUtils jwtUtils;
    private EmailSender emailSender;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserService userService,
                           PasswordEncoder encoder, JwtUtils jwtUtils, EmailSender emailSender) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.emailSender = emailSender;
    }

    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getName(),
                userDetails.getUsername(),
                userDetails.getRole(),
                userDetails.getStatus());
    }

    @Override
    public ResponseEntity registerUser(SignupRequest signUpRequest) {
        if (userService.existsByEmail(signUpRequest.getEmail())) {
            throw new NotFoundException("Error: Email is already taken!");
        }

        if (userService.existsByName(signUpRequest.getName())) {
            throw new NotFoundException("Error: Name is already taken!");
        }

        if (signUpRequest.getExchange() == null) {
            CreateUser user = new CreateUser(signUpRequest.getEmail(),
                    signUpRequest.getName(),
                    encoder.encode(signUpRequest.getPassword()),
                    signUpRequest.getRole());
            userService.register(user);
        } else {
            CreateBroker broker = new CreateBroker(signUpRequest.getEmail(),
                    signUpRequest.getName(),
                    encoder.encode(signUpRequest.getPassword()),
                    signUpRequest.getRole(),
                    signUpRequest.getExchange());
            userService.registerBroker(broker);
        }
        emailSender.sendMessage(signUpRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
