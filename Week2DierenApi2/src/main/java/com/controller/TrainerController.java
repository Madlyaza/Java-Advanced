package com.controller;

import com.model.Pokemon;
import com.model.Trainer;
import com.service.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/trainer")
public class TrainerController
{
    private final TrainerService trainerService;
    private TrainerController(TrainerService trainerService)
    {
        this.trainerService = trainerService;
    }

    @GetMapping
    public ResponseEntity<ArrayList<Trainer>> getAll(@RequestParam(required = false) String name)
    {
        if(name != null)
        {
            return trainerService.getTrainerByName(name);
        }
        else
        {
            return new ResponseEntity<>(trainerService.getTrainers(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable int id)
    {
        return trainerService.getTrainerById(id);
    }

    @GetMapping("/{id}/pokemon")
    public ResponseEntity<ArrayList<Pokemon>> getTrainersPokemon(@PathVariable int id)
    {
        return trainerService.getTrainersPokemon(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Trainer> create(@RequestBody Trainer newTrainer)
    {
        return trainerService.create(newTrainer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Trainer> delete(@PathVariable int id)
    {
        return trainerService.delete(id);
    }

    @PutMapping(value = "",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Trainer> update(@RequestParam int id, @RequestBody Trainer trainer)
    {
        return trainerService.update(id, trainer);
    }
}
