package com.example.developerservice;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "developers")
@Data
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Integer foundingYear;
}