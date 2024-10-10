package com.nnamanibenjamin.E_commerce.dto;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    @NotBlank(message = "Product name is required")
    private String name;
    @NotBlank(message = "Product description is required")
    private String description;
    @Positive(message = "Price must be positive")
    private Integer quantity;
    @PositiveOrZero(message = "Price must be positive or zero ")
    private BigDecimal price;
    private List<CommentDto> comments;
}
