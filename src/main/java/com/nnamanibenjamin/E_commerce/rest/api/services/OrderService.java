package com.nnamanibenjamin.E_commerce.rest.api.services;


import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.nnamanibenjamin.E_commerce.rest.api.dto.CartDto;
import com.nnamanibenjamin.E_commerce.rest.api.dto.OrderDto;
import com.nnamanibenjamin.E_commerce.rest.api.exception.InsufficientStockException;
import com.nnamanibenjamin.E_commerce.rest.api.exception.ResourceNotFoundException;
import com.nnamanibenjamin.E_commerce.rest.api.mapper.CartMapper;
import com.nnamanibenjamin.E_commerce.rest.api.mapper.OrderMapper;
import com.nnamanibenjamin.E_commerce.rest.api.model.Cart;
import com.nnamanibenjamin.E_commerce.rest.api.model.Order;
import com.nnamanibenjamin.E_commerce.rest.api.model.OrderItem;
import com.nnamanibenjamin.E_commerce.rest.api.model.Product;
import com.nnamanibenjamin.E_commerce.rest.api.model.User;
import com.nnamanibenjamin.E_commerce.rest.api.repository.OrderRepository;
import com.nnamanibenjamin.E_commerce.rest.api.repository.ProductRepository;
import com.nnamanibenjamin.E_commerce.rest.api.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CartService cartService;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final CartMapper cartMapper;
    private final OrderMapper orderMapper;
    
    @Transactional
    public OrderDto createOrder(Long userId, String address, String phoneNumber) {
       User user = userRepository.findById(userId).orElseThrow(()-> new        ResourceNotFoundException("User not found")); 

       if(!user.isEmailConfirmation()){
        throw new IllegalStateException("Email not comfirmed. please comfirm email before placing order");
       }
       CartDto cartDto = cartService.getCart(userId);
       Cart cart = cartMapper.toEntity(cartDto);

       if(cart.getItems().isEmpty()) {
           throw new IllegalStateException("You cannot create an order if your cart is empty");
    }
    Order order = new Order();
    order.setAddress(address);
    order.setPhoneNumber(phoneNumber);
    order.setCreatedAt(LocalDateTime.now());
    order.setStatus(Order.OrderStatus.PREPARING);
    order.setUser(user);
    
    List<OrderItem> orderItems = createOrder(cart, order);
    order.setItems(orderItems);
    
    Order savedOrder = orderRepository.save(order);
    cartService.clearCart(userId);

    try {
        emailService.sendOrderConfirmation(order);
    } catch (MailException e) {
        logger.error("Failed to send order confirmation for Order Id" + order.getId(), e);
    }
    return orderMapper.toDto(savedOrder);
    }

    private List<OrderItem> createOrder(Cart cart, Order order) {
        
        return cart.getItems().stream().map(cartItem -> {
            Product product = productRepository.findById(cartItem.getProduct().getId()).orElseThrow(()-> new EntityNotFoundException("Product not found with id: " + cartItem.getProduct().getId())); 

            if(product.getQuantity() == null) {
                throw new IllegalStateException("Not enough stock available");
            }
            if(product.getQuantity() < cartItem.getQuantity()) {
                throw new InsufficientStockException("Not enough stock available");
            }
            product.setQuantity(product.getQuantity() - cartItem.getQuantity());
            productRepository.save(product);
            return new OrderItem(null, order, product, cartItem.getQuantity(), product.getPrice());
        }).collect(Collectors.toList());
    }

    public List<OrderDto> getAllOrders() {
        return orderMapper.toDtos(orderRepository.findAll());
    }

    public List<OrderDto> getUserOrders(Long userId) {
        return orderMapper.toDtos(orderRepository.findByUserId(userId));
    }
    
   public OrderDto updateOrderStatus(Long orderId, Order.OrderStatus status) {
       Order order = orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order not found with id: " + orderId)); 
       order.setStatus(status);
       Order updatedOrder = orderRepository.save(order);
       return orderMapper.toDto(updatedOrder);
   }
} 
