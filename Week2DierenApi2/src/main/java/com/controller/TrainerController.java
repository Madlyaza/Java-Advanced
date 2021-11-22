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
import java.util.ArrayList;
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

    @GetMapping("")
    public ResponseEntity<ArrayList<Trainer>> getAll(@RequestParam(required = false) String name, @RequestParam(required = false) String id)
    {
        System.out.println();
        if(name != null)
        {
            return trainerService.getTrainerByName(name);
        }
        else if (id != null)
        {
            return trainerService.getTrainerById(Integer.parseInt(id));
        }
        else
        {
            return new ResponseEntity<>(trainerService.getTrainers(), HttpStatus.OK);
        }
    }

//    @GetMapping("/{name}")
//    public ResponseEntity<ArrayList<Trainer>> getByName(@PathVariable String name)
//    {
//        return trainerService.getTrainerByName(name);
//    }

    @GetMapping("/caught")
    public ResponseEntity<ArrayList<Pokemon>> getTrainersPokemon(@RequestParam int id)
    {
        return trainerService.getTrainersPokemon(id);
    }

    @PostMapping(value = "",
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Trainer> create(@RequestBody @Valid Trainer newTrainer)
    {
        return trainerService.create(newTrainer);
    }

    @DeleteMapping("")
    public ResponseEntity<Trainer> delete(@RequestParam int id)
    {
        return trainerService.delete(id);
    }

    @PutMapping(value = "",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Trainer> update(@RequestParam int id, @RequestBody @Valid Trainer trainer)
    {
        return trainerService.update(id, trainer);
    }

}
