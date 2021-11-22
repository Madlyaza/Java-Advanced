package com.controller;

import com.model.Pokemon;
import com.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController

@RequestMapping("/pokemon")
public class PokemonController
{
    private final PokemonService pokemonService;

    private PokemonController(PokemonService pokemonService)
    {
        this.pokemonService = pokemonService;
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<Pokemon>> getAll()
    {
        return new ResponseEntity<>(pokemonService.getPokemon(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<ArrayList<Pokemon>> getByName(@PathVariable String name)
    {
        return pokemonService.getPokemonByName(name);
    }

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pokemon> create(@RequestBody @Valid Pokemon newPokemon)
    {
        return pokemonService.create(newPokemon);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pokemon> delete(@PathVariable int id)
    {
        return pokemonService.delete(id);
    }

    @PutMapping(value = "/{id}",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pokemon> update(@PathVariable int id, @RequestBody @Valid Pokemon pokemonToUpdate)
    {
        return pokemonService.update(id, pokemonToUpdate);
    }
}
