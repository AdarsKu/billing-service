package com.billing_service.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class CustomerEmailService {

	private static final Logger log= LoggerFactory.getLogger(CustomerEmailService.class);
    private final JavaMailSender mailSender;

    public CustomerEmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    @EventListener
    public void senMail(CustomerCreatedEvent event) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(event.getEmail());
        message.setSubject("Email sent by billing service of patient service");
        message.setText(
            "Hi " + event.getName() + ",\n\n" +
            "Welcome! Your account has been created successfully."
        );
        log.info("email send successfully to the {}",event.getName());
        mailSender.send(message);
    }
}
