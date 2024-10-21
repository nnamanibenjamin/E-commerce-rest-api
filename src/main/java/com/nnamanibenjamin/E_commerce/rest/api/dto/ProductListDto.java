package com.nnamanibenjamin.E_commerce.rest.api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductListDto {
    private Long id;
    @NotBlank(message = "Product name is required")
    private String name;
    @NotBlank(message = "Description is required")
    private String description;
    @PositiveOrZero(message = "Price must be positive or zero ")
    private Integer quantity;
    @Positive(message = "Price must be positive")
    private BigDecimal price;
    @NotBlank 
    private String image;
}
