package com.nnamanibenjamin.E_commerce.rest.api.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nnamanibenjamin.E_commerce.rest.api.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long id);
}
