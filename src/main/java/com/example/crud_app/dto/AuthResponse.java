package com.example.crud_app.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String id;
    private String name;
    private String email;
    private String role;
}