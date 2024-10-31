package com.nnamanibenjamin.E_commerce.rest.api.services;

import org.springframework.stereotype.Service;

import com.nnamanibenjamin.E_commerce.rest.api.dto.CartDto;
import com.nnamanibenjamin.E_commerce.rest.api.exception.InsufficientStockException;
import com.nnamanibenjamin.E_commerce.rest.api.exception.ResourceNotFoundException;
import com.nnamanibenjamin.E_commerce.rest.api.mapper.CartMapper;
import com.nnamanibenjamin.E_commerce.rest.api.model.Cart;
import com.nnamanibenjamin.E_commerce.rest.api.model.Product;
import com.nnamanibenjamin.E_commerce.rest.api.model.User;
import com.nnamanibenjamin.E_commerce.rest.api.repository.CartRepository;
import com.nnamanibenjamin.E_commerce.rest.api.repository.ProductRepository;
import com.nnamanibenjamin.E_commerce.rest.api.repository.UserRepository;


import java.util.ArrayList;

import java.util.Optional;

import com.nnamanibenjamin.E_commerce.rest.api.model.CartItem;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartMapper cartMapper;


    public CartDto addToCart(Long userId, Long productId, Integer quantity) {
       
        User user = userRepository.findById(userId).orElseThrow(
            () -> new InsufficientStockException("User not found")
        );
        Product product = productRepository.findById(productId).orElseThrow(
            () -> new InsufficientStockException("Product not found")
        );
        if(product.getQuantity() < quantity) {
            throw new InsufficientStockException("Not enough stock  available");
        }

        Cart cart = cartRepository.findByUserId(userId).orElse(new Cart(null, user, new ArrayList<>()));
        
        Optional<CartItem> existingCartItem = cart.getItems().stream()
            .filter(item -> item.getProduct().getId().equals(productId))
            .findFirst();
        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            CartItem cartItem = new CartItem(null, cart, product, quantity);
            cart.getItems().add(cartItem);
        }
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toDto(savedCart); 
    }

    public CartDto getCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow(
            () -> new ResourceNotFoundException("Cart not found")
        );
        return cartMapper.toDto(cart);
    }
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow(
            () -> new ResourceNotFoundException("Cart not found")
        );
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}
