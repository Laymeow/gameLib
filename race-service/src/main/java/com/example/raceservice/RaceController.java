package com.example.raceservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/races")
public class RaceController {
    @Autowired
    private RaceService service;

    @PostMapping
    public Race addRace(@RequestBody Race race) {
        return service.addRace(race);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Race> getRaceById(@PathVariable Long id) {
        return service.getRaceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/winner")
    public ResponseEntity<Race> getLastRaceWithWinner() {
        Race race = service.getLastRaceWithWinner();
        return race != null ? ResponseEntity.ok(race) : ResponseEntity.notFound().build();
    }
}