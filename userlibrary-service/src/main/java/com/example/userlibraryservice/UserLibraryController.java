package com.example.userlibraryservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/userlibrary")
public class UserLibraryController {

    @Autowired
    private UserLibraryRepository userLibraryRepository;

    @GetMapping
    public List<UserLibrary> getAllUserLibraries() {
        return userLibraryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserLibrary> getUserLibraryById(@PathVariable Long id) {
        Optional<UserLibrary> userLibrary = userLibraryRepository.findById(id);
        return userLibrary.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<UserLibrary> getUserLibraryByUserId(@PathVariable Long userId) {
        return userLibraryRepository.findByUserId(userId);
    }

    @PostMapping
    public UserLibrary addGameToLibrary(@RequestBody UserLibrary userLibrary) {
        userLibrary.setPurchaseDate(LocalDate.now());
        return userLibraryRepository.save(userLibrary);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserLibrary(@PathVariable Long id) {
        if (userLibraryRepository.existsById(id)) {
            userLibraryRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}