package com.nnamanibenjamin.E_commerce.rest.api.services;

import com.nnamanibenjamin.E_commerce.rest.api.dto.ChangePasswordRequest;
import com.nnamanibenjamin.E_commerce.rest.api.exception.ResourceNotFoundException;
import com.nnamanibenjamin.E_commerce.rest.api.model.User;
import com.nnamanibenjamin.E_commerce.rest.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(User.Role.USER);
        return userRepository.save(user);
    }  
    
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()-> new  ResourceNotFoundException("User not found"));  
    }   

    public void changePassword(String email, ChangePasswordRequest request){
        User user = getUserByEmail(email);
        if (!passwordEncoder.matches(request.getCurrentPassword (), user.getPassword())) {
            throw new BadCredentialsException("Current password does not match");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user); 
    }
}
 