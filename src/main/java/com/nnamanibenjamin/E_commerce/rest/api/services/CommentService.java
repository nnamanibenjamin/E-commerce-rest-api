package com.nnamanibenjamin.E_commerce.rest.api.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nnamanibenjamin.E_commerce.rest.api.dto.CommentDto;
import com.nnamanibenjamin.E_commerce.rest.api.exception.InsufficientStockException;
import com.nnamanibenjamin.E_commerce.rest.api.mapper.CommentMapper;
import com.nnamanibenjamin.E_commerce.rest.api.model.Comment;
import com.nnamanibenjamin.E_commerce.rest.api.model.Product;
import com.nnamanibenjamin.E_commerce.rest.api.model.User;
import com.nnamanibenjamin.E_commerce.rest.api.repository.CommentRepository;
import com.nnamanibenjamin.E_commerce.rest.api.repository.ProductRepository;
import com.nnamanibenjamin.E_commerce.rest.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ProductRepository  productRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    public CommentDto addComment(Long productId,Long userId, CommentDto commentDto) {    
        Product product = productRepository.findById(productId).orElseThrow(
            () -> new InsufficientStockException("Product not found")
        );  
        User user = userRepository.findById(userId).orElseThrow(
            () -> new InsufficientStockException("User not found")
        ); 
        Comment comment = commentMapper.toEntity(commentDto);
        comment.setProduct(product);
        comment.setUser(user);
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toDto(savedComment); 
    }

    public List<CommentDto> getCommentByProduct(Long productId) {
         
        List<Comment> comments = commentRepository.findByProductId(productId);

        return comments.stream()
            .map(commentMapper::toDto)
            .collect(Collectors.toList());
        
    }
}
