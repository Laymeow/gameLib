package com.example.raceservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "driver-service", url = "${driver.service.url}")
public interface DriverClient {
    @GetMapping("/drivers/{id}")
    DriverDTO getDriverById(@PathVariable("id") Long id);
}
