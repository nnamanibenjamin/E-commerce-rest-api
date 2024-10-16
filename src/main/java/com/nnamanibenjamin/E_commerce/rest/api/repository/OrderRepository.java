package com.nnamanibenjamin.E_commerce.rest.api.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nnamanibenjamin.E_commerce.rest.api.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}
