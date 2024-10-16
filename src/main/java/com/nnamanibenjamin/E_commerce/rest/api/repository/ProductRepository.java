package com.nnamanibenjamin.E_commerce.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnamanibenjamin.E_commerce.rest.api.model.Product;

public interface ProductRepository extends JpaRepository<Product , Long> {
    
}
