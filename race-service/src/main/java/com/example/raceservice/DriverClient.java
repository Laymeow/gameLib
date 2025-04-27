package com.example.raceservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Driver;

@FeignClient(name = "driver-service")
public interface DriverClient {
    @GetMapping("/drivers/{id}")
    Driver getDriverById(@PathVariable("id") Long id);
}
