package stock_exchange.service;


import stock_exchange.model.request.SignupRequest;

public interface EmailSender {
    void sendMessage(SignupRequest user);
}
