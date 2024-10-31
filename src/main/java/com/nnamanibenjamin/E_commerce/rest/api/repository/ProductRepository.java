package com.nnamanibenjamin.E_commerce.rest.api.repository;



import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nnamanibenjamin.E_commerce.rest.api.dto.ProductListDto;
import com.nnamanibenjamin.E_commerce.rest.api.model.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT new com.nnamanibenjamin.E_commerce.rest.api.dto.ProductListDto(p.id, p.name, p.description, p.quantity, p.price, p.image) FROM Product p")
    org.springframework.data.domain.Page<ProductListDto> findAllWithoutComments(Pageable pageable);
}
