package com.controller;

import com.model.Pokemon;
import com.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/pokemon")
public class PokemonController
{
    private final PokemonService pokemonService;

    private PokemonController(PokemonService pokemonService)
    {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public ResponseEntity<List<Pokemon>> getPokemon(@RequestParam(required = false) String name)
    {
        if(name != null)
        {
            return new ResponseEntity<>(pokemonService.getPokemonByName(name), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(pokemonService.getPokemon(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable Integer id)
    {
        return new ResponseEntity<>(pokemonService.getPokemonById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pokemon> create(@RequestBody Pokemon newPokemon)
    {
        return new ResponseEntity<>(pokemonService.create(newPokemon), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pokemon> delete(@PathVariable int id)
    {
        return new ResponseEntity<>(pokemonService.delete(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pokemon> update(@PathVariable Integer id, @RequestBody Pokemon pokemonToUpdate)
    {
        return new ResponseEntity<>(pokemonService.update(id, pokemonToUpdate), HttpStatus.OK);
    }
}
