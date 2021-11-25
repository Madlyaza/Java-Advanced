package com.controller;

import com.model.Pokemon;
import com.service.PokemonService;
import org.springframework.http.HttpStatus;
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

    // TODO: remake this, fix it etc
//    @PostMapping(value = "",
//            consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Pokemon> create(@RequestBody Pokemon newPokemon)
//    {
//        return pokemonService.create(newPokemon);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Pokemon> delete(@PathVariable int id)
//    {
//        return pokemonService.delete(id);
//    }
//
//    @PutMapping(value = "/{id}",
//    consumes = MediaType.APPLICATION_JSON_VALUE,
//    produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Pokemon> update(@PathVariable int id, @RequestBody Pokemon pokemonToUpdate)
//    {
//        return pokemonService.update(id, pokemonToUpdate);
//    }
}
