package com.nnamanibenjamin.E_commerce.rest.api.repository;

import java.util.Optional;  
import org.springframework.data.jpa.repository.JpaRepository;

import com.nnamanibenjamin.E_commerce.rest.api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
