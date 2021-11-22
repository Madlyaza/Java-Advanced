package com.controller;

import com.model.Trainer;
import com.service.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController

@RequestMapping("/trainer")
public class TrainerController
{
    private final TrainerService trainerService;
    private TrainerController(TrainerService trainerService)
    {
        this.trainerService = trainerService;
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<Trainer>> getAll()
    {
        return new ResponseEntity<>(trainerService.getTrainers(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<ArrayList<Trainer>> getByName(@PathVariable String name)
    {
        return trainerService.getTrainerByName(name);
    }

    @PostMapping(value = "",
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Trainer> create(@RequestBody @Valid Trainer newTrainer)
    {
        return trainerService.create(newTrainer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Trainer> delete(@PathVariable int id)
    {
        return trainerService.delete(id);
    }

    @PutMapping(value = "/{id}",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Trainer> update(@PathVariable int id, @RequestBody @Valid Trainer trainer)
    {
        return trainerService.update(id, trainer);
    }
}
