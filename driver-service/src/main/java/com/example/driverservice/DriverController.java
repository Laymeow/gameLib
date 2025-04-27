package com.example.driverservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private TeamClient teamClient;

    @PostMapping
    public Map<String, Object> createDriver(@RequestBody Driver driver) {
        Driver savedDriver = driverRepository.save(driver);
        return mapDriverToResponse(savedDriver);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getDriverById(@PathVariable Long id) {
        Optional<Driver> driverOptional = driverRepository.findById(id);
        if (driverOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Driver driver = driverOptional.get();
        return ResponseEntity.ok(mapDriverToResponse(driver));
    }

    @GetMapping
    public List<Map<String, Object>> getAllDrivers() {
        return driverRepository.findAll().stream()
                .map(this::mapDriverToResponse)
                .toList();
    }

    private Map<String, Object> mapDriverToResponse(Driver driver) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", driver.getId());
        response.put("name", driver.getName());
        response.put("nationality", driver.getNationality());
        response.put("age", driver.getAge());

        try {
            Team team = teamClient.getTeamById(driver.getTeamId());
            response.put("team", team != null ? team.getName() : null);
        } catch (Exception e) {
            System.err.println("Failed to fetch team for ID " + driver.getTeamId() + ": " + e.getMessage());
            response.put("team", null);
        }

        return response;
    }
}