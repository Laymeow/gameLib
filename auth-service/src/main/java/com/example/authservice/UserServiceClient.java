package com.example.authservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @PostMapping("/api/users/register")
    ResponseEntity<String> register(@RequestBody RegisterRequest request);

    @GetMapping("/api/users/username/{username}")
    User getUserByUsername(@PathVariable("username") String username);
}