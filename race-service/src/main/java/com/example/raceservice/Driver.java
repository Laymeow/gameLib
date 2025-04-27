package com.example.raceservice;

public class Driver {
    private Long id;
    private String name;
    private String nationality;
    private Integer age;
    private String team;

    // Default constructor (required for deserialization)
    public Driver() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getTeam() { return team; }
    public void setTeam(String team) { this.team = team; }
}