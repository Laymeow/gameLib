package com.example.developerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DeveloperServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeveloperServiceApplication.class, args);
    }
}