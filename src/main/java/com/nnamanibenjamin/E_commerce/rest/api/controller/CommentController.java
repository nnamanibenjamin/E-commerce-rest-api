package com.nnamanibenjamin.E_commerce.rest.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nnamanibenjamin.E_commerce.rest.api.dto.CommentDto;
import com.nnamanibenjamin.E_commerce.rest.api.model.User;
import com.nnamanibenjamin.E_commerce.rest.api.services.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
     
    @PostMapping("/product/{productId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CommentDto> addComment(@PathVariable  Long productId, @AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody CommentDto commentDto) {
        Long userId = ((User) userDetails).getId() ;
        return ResponseEntity.ok(commentService.addComment(productId,userId,commentDto));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<CommentDto>> getCommentByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(commentService.getCommentByProduct(productId));
    } 

}
