package com.example.developerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/developers")
public class DeveloperController {

    @Autowired
    private DeveloperRepository developerRepository;

    @GetMapping
    public List<Developer> getAllDevelopers() {
        return developerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Developer> getDeveloperById(@PathVariable Long id) {
        Optional<Developer> developer = developerRepository.findById(id);
        return developer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Developer createDeveloper(@RequestBody Developer developer) {
        return developerRepository.save(developer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Developer> updateDeveloper(@PathVariable Long id, @RequestBody Developer updatedDeveloper) {
        Optional<Developer> existingDeveloper = developerRepository.findById(id);
        if (existingDeveloper.isPresent()) {
            updatedDeveloper.setId(id);
            return ResponseEntity.ok(developerRepository.save(updatedDeveloper));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeveloper(@PathVariable Long id) {
        if (developerRepository.existsById(id)) {
            developerRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}