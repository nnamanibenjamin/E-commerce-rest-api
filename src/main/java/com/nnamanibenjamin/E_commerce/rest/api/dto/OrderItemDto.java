package com.nnamanibenjamin.E_commerce.rest.api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderItemDto {
    private Long id;
    private Long productId;
    @Positive
    private Integer quantity;
    @Positive
    private BigDecimal price;
}
