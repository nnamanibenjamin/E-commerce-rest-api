package com.nnamanibenjamin.E_commerce.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderItemDto {

    private Long id;    

    private Long productId;

    private Integer quantity;

    private BigDecimal price;
}
