package com.example.driverservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {
    @Autowired
    private DriverRepository repository;

    public List<Driver> getAllDrivers() {
        return repository.findAll();
    }

    public Driver addDriver(Driver driver) {
        return repository.save(driver);
    }

    public Optional<Driver> getDriverById(Long id) {
        return repository.findById(id);
    }
}