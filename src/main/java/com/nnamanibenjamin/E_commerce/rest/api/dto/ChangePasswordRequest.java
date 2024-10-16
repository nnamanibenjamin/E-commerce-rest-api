package com.nnamanibenjamin.E_commerce.rest.api.dto;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String currentPassword;
    private String newPassword;
}
