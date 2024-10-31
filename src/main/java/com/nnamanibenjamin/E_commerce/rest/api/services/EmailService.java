package com.nnamanibenjamin.E_commerce.rest.api.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.nnamanibenjamin.E_commerce.rest.api.model.Order;
import com.nnamanibenjamin.E_commerce.rest.api.model.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties.Simple;

import lombok.RequiredArgsConstructor; 

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    @Value("spring.mail.username")
    private String fromEmail;

    public void sendOrderConfirmation(Order order){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail); 
        message.setTo(order.getUser().getEmail());
        message.setSubject("Order Confirmation");
        message.setText("Your order has been confirmed." + order.getId() + " Thank you for shopping with us.");
        mailSender.send(message);
    }

     public void sendComfirmationEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail); 
        message.setTo(user .getEmail());
        message.setSubject("Confirmation your email");
        message.setText("Please confirm your email by entering the code below : " + user.getConfirmationCode());
        mailSender.send(message); 
     }
} 
