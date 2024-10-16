package com.nnamanibenjamin.E_commerce.rest.api.repository;

import java.util.List; 
import org.springframework.data.jpa.repository.JpaRepository;

import com.nnamanibenjamin.E_commerce.rest.api.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByProductId(Long productId);
}
