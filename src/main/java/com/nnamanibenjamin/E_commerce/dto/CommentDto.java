package com.nnamanibenjamin.E_commerce.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    @NotBlank(message = "Content cannot be blank")
    private String content;
    @Min(value = 1, message = "Score must be greater than or equal to 1")   
    @Max(value = 5, message = "Score must be less than or equal to 5")
    private Integer score;
    private Long productId;
    private Long userId;

}
