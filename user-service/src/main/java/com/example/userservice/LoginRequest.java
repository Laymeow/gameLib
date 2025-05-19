package com.example.userservice;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}