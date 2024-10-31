package com.nnamanibenjamin.E_commerce.rest.api.dto;

import lombok.Data;

@Data
public class EmailConfirmationRequest {
    private String email;
    private String confirmationCode;
}
