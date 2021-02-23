package stock_exchange.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import stock_exchange.model.request.SignupRequest;
import stock_exchange.service.EmailSender;

@Service
public class EmailSenderImpl implements EmailSender {

    @Value("${spring.mail.username}")
    private String username;

    private final JavaMailSender emailSender;

    @Autowired
    public EmailSenderImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendMessage(SignupRequest newUser) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(newUser.getEmail());
        message.setSubject("Registration");
        message.setText("You've been registered!\n"
                + "Your email: " + newUser.getName() + "\n"
                + "Your password:" + newUser.getPassword() );
        emailSender.send(message);
    }
}
