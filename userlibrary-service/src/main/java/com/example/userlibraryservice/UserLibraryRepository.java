package com.example.userlibraryservice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLibraryRepository extends JpaRepository<UserLibrary, Long> {
    List<UserLibrary> findByUserId(Long userId);
}