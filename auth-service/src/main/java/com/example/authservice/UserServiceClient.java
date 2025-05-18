package com.example.authservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@FeignClient(name = "user-service")
public interface UserServiceClient {
    @GetMapping("/api/users/{id}")
    User getUserById(@PathVariable("id") Long id);
    @GetMapping("/api/users/email/{email}")
    User getUserByEmail(@PathVariable("email") String email);
    @GetMapping("/api/users/username/{username}")
    User getUserByUsername(@PathVariable("username") String username);

}