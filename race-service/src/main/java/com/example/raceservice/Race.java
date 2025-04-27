package com.example.raceservice;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;
    private LocalDate date;
    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Result> results = new ArrayList<>();


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public List<Result> getResults() { return results; }
    public void setResults(List<Result> results) { this.results = results; }
}