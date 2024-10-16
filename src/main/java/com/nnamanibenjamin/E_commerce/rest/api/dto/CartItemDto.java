package com.nnamanibenjamin.E_commerce.rest.api.dto;

import lombok.Data;

@Data
public class CartItemDto {
private Long id;
private Long productId;
private Integer quantity;
}
