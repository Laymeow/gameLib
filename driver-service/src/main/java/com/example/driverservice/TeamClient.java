package com.example.driverservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "team-service")
public interface TeamClient {
    @GetMapping("/teams/{id}")
    Team getTeamById(@PathVariable("id") Long id);
}