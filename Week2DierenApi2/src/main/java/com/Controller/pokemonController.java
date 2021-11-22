package com.Controller;

import com.Model.Pokemon;
import com.Service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController

@RequestMapping("/pokemon")
public class pokemonController
{
    private final PokemonService pokemonService;

    private pokemonController(PokemonService pokemonService)
    {
        this.pokemonService = pokemonService;
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<Pokemon>> getAll()
    {
        return new ResponseEntity<>(pokemonService.getPokemon(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Pokemon> getByName(@PathVariable String name)
    {
        return pokemonService.getPokemonByName(name);
    }

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pokemon> create(@RequestBody Pokemon newPokemon)
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
    public ResponseEntity<Pokemon> update(@PathVariable int id, @RequestBody Pokemon pokemonToUpdate)
    {
        return pokemonService.update(id, pokemonToUpdate);
    }
}
