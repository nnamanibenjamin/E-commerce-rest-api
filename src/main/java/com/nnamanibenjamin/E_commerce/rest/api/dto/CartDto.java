package com.nnamanibenjamin.E_commerce.rest.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class CartDto {
    private Long id;
    private Long userId;
    private List<CartItemDto> items;
}
