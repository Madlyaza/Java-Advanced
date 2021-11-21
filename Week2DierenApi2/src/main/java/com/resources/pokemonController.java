package com.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.ArrayList;

@RestController

@RequestMapping("/pokemon")
public class pokemonController
{
    ArrayList<Pokemon> pokemonList;

    private pokemonController()
    {
        Pokemon pokemon = new Pokemon();
        pokemon.setName("Charmander");
        pokemon.setIdTag(12);
        pokemon.setTrainerId(1);

        this.pokemonList = new ArrayList<>();

        pokemonList.add(pokemon);
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<Pokemon>> getAll()
    {
        return new ResponseEntity<>(pokemonList, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Pokemon> getByName(@PathVariable String name) throws Exception
    {
        for (Pokemon pokemon:pokemonList)
        {
            if (pokemon.name.equals(name))
            {
                return new ResponseEntity<>(pokemon, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pokemon> create(@RequestBody Pokemon newPokemon) throws Exception
    {
        if(pokemonList.add(newPokemon))
        {
            return new ResponseEntity<>(newPokemon, HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pokemon> delete(@PathVariable int id)
    {
        for (int i = 0; i < pokemonList.size(); i++)
        {
            if (pokemonList.get(i).idTag == id)
            {
                return new ResponseEntity<>(pokemonList.remove(i), HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
