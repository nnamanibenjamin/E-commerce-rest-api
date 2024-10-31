package com.nnamanibenjamin.E_commerce.rest.api.services;

import com.nnamanibenjamin.E_commerce.rest.api.dto.ChangePasswordRequest;
import com.nnamanibenjamin.E_commerce.rest.api.exception.InsufficientStockException;
import com.nnamanibenjamin.E_commerce.rest.api.model.User;
import com.nnamanibenjamin.E_commerce.rest.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Random;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public User registerUser(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(User.Role.USER);
        user.setConfirmationCode(generateConfirmationCode());
        user.setEmailConfirmation(false);
        emailService.sendComfirmationEmail(user);
        return userRepository.save(user);
    }  
    
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()-> new  InsufficientStockException("User not found"));  
    }   

    public void changePassword(String email, ChangePasswordRequest request){
        User user = getUserByEmail(email);
        if (!passwordEncoder.matches(request.getCurrentPassword (), user.getPassword())) {
            throw new BadCredentialsException("Current password does not match");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user); 
    }

    public void confirmEmail(String email, String comfirmationCode) {
        User user = getUserByEmail(email);
        if (user.getConfirmationCode().equals(comfirmationCode)) {
            user.setEmailConfirmation(true);
            user.setConfirmationCode(null);
            userRepository.save(user);
        }else{
            throw new BadCredentialsException("Invalid confirmation code");
        }         
    }

    private String generateConfirmationCode() {
        Random random = new Random();
        int code = 1000000 + random.nextInt(9000000);
        return String.valueOf(code);  
    }

    }
 