package com.nnamanibenjamin.E_commerce.rest.api.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.nnamanibenjamin.E_commerce.rest.api.model.Order;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderDto {
    private Long id;
    private Long userId;
    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
    private LocalDateTime createdAt;
    private Order.OrderStatus status;
    private List<OrderItemDto> items;
}
