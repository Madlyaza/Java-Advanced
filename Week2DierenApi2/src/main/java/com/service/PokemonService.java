package com.service;

import com.model.Pokemon;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class PokemonService
{
    private final ArrayList<Pokemon> pokemonList;

    public PokemonService(ArrayList<Pokemon> pokemonList)
    {
        this.pokemonList = pokemonList;
    }

    public ArrayList<Pokemon> getPokemon()
    {
        return pokemonList;
    }

    public ResponseEntity<ArrayList<Pokemon>> getPokemonByName(String name)
    {
        if(name.equals(""))
        {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        ArrayList<Pokemon> matchedPokemonList = new ArrayList<>();

        for (Pokemon pokemon: pokemonList)
        {
            if (pokemon.getName().contains(name))
            {
                matchedPokemonList.add(pokemon);
            }
        }
        if(matchedPokemonList.size() != 0)
        {
            return new ResponseEntity<>(matchedPokemonList, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Pokemon> create(Pokemon pokemon)
    {
        Pokemon newPokemon = new Pokemon();
        newPokemon.setName(pokemon.getName());
        newPokemon.setId(pokemonList.get(pokemonList.size()-1).getId()+1);

        if(pokemonList.add(newPokemon))
        {
            return new ResponseEntity<>(newPokemon, HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<Pokemon> delete(int id)
    {
        for (int i = 0; i < pokemonList.size(); i++)
        {
            if (pokemonList.get(i).getId() == id)
            {
                return new ResponseEntity<>(pokemonList.remove(i), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Pokemon> update(int id, Pokemon pokemonToUpdate)
    {
        for (Pokemon pokemon : pokemonList)
        {
            if (pokemon.getId() == id)
            {
                pokemon.setName(pokemonToUpdate.getName());
                return new ResponseEntity<>(pokemon, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
