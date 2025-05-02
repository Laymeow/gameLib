package com.example.userlibraryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserLibraryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserLibraryServiceApplication.class, args);
    }
}