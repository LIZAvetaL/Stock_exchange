package stock_exchange.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import stock_exchange.service.EmailSender;

@Service
public class EmailSenderImpl implements EmailSender {

    @Value("${spring.mail.username}")
    private String username;

    private final JavaMailSender emailSender;

    @Autowired
    public EmailSenderImpl(@Qualifier("getJavaMailSender") JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendMessage(String newUserEmail) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(newUserEmail);
        message.setSubject("Stock Exchange");
        message.setText(" Registration was a success");
        emailSender.send(message);
    }
}
