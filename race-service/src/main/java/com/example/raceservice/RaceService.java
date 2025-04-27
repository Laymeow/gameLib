package com.example.raceservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.raceservice.DriverDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RaceService {
    @Autowired
    private RaceRepository raceRepository;
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private DriverClient driverClient;

    public Race addRace(Race race) {
        if (race.getResults() != null && !race.getResults().isEmpty()) {
            List<Result> validatedResults = new ArrayList<>();
            for (Result result : race.getResults()) {
                Long driverId = result.getDriverId();
                try {
                    DriverDTO driver = driverClient.getDriverById(driverId);
                    if (driver != null) {
                        result.setRace(race);
                        validatedResults.add(result);
                    } else {
                        System.err.println("Driver with ID " + driverId + " not found, skipping result");
                    }
                } catch (Exception e) {
                    System.err.println("Failed to fetch driver with ID " + driverId + ": " + e.getMessage());
                }
            }
            race.setResults(validatedResults);
        }
        return raceRepository.save(race);
    }

    public Optional<Race> getRaceById(Long id) {
        return raceRepository.findById(id);
    }

    public Race getLastRaceWithWinner() {
        Race lastRace = raceRepository.findAll().stream()
                .max((r1, r2) -> r1.getDate().compareTo(r2.getDate()))
                .orElse(null);
        if (lastRace != null) {
            Result winner = lastRace.getResults().stream()
                    .filter(r -> r.getPosition() == 1)
                    .findFirst()
                    .orElse(null);
            if (winner != null) {
                DriverDTO driver = driverClient.getDriverById(winner.getDriverId());
            }
        }
        return lastRace;
    }

}