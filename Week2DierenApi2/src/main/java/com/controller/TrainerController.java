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
    public ResponseEntity<List<Trainer>> getAll(@RequestParam(required = false) String name)
    {
        if(name != null)
        {
            return new ResponseEntity<>(trainerService.getTrainerByName(name), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(trainerService.getTrainers(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable int id)
    {
        return new ResponseEntity<>(trainerService.getTrainerById(id), HttpStatus.OK);
    }

//    TODO: Fix this method
//    @GetMapping("/{id}/pokemon")
//    public ResponseEntity<ArrayList<Pokemon>> getTrainersPokemon(@PathVariable int id)
//    {
//        return trainerService.getTrainersPokemon(id);
//    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Trainer> create(@RequestBody Trainer newTrainer)
    {
        return new ResponseEntity<>(trainerService.create(newTrainer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Trainer> delete(@PathVariable Integer id)
    {
        return new ResponseEntity<>(trainerService.delete(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Trainer> update(@PathVariable Integer id, @RequestBody Trainer trainer)
    {
        return new ResponseEntity<>(trainerService.update(id, trainer), HttpStatus.OK);
    }
}
