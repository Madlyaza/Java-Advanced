package com.service;

import com.exception.DataNotFoundException;
import com.exception.MalformedInformationException;
import com.model.Pokemon;
import com.repository.PokemonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonService
{
    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository)
    {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> getPokemon()
    {
        return pokemonRepository.getPokemon();
    }

    public List<Pokemon> getPokemonByName(String name)
    {
        if(name.equals(""))
        {
            throw new DataNotFoundException("Name search cannot be empty.");
        }

        List<Pokemon> matchedPokemonList = new ArrayList<>(pokemonRepository.getPokemonByName(name));
        if(matchedPokemonList.size() == 0)
        {
            throw new DataNotFoundException("Result was empty");
        }

        return matchedPokemonList;
    }

    public Pokemon getPokemonById(Integer id)
    {
        if(id == 0)
        {
            throw new DataNotFoundException("Name search cannot be empty");
        }

        Pokemon matchedPokemon = pokemonRepository.getPokemonById(id);
        System.out.println("----------------------------------" + matchedPokemon.getName() + " " + matchedPokemon.getId());
        // TODO FIX THIS SOMETHING IS BROKEN DUNNO DOES SOME INFINITE LOOP SHIT
        return matchedPokemon;
    }

    // TODO: unload this, fix it etc
//    public ResponseEntity<Pokemon> create(Pokemon pokemon)
//    {
//        Pokemon newPokemon = new Pokemon();
//        newPokemon.setName(pokemon.getName());
//
//        if(pokemonList.add(newPokemon))
//        {
//            return new ResponseEntity<>(newPokemon, HttpStatus.CREATED);
//        }
//        else
//        {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//    }
//
    public Pokemon delete(int id)
    {
        if(id == 0)
        {
            throw new MalformedInformationException("Id cannot be 0");
        }
        return pokemonRepository.deletePokemon(id);
    }
//
//    public ResponseEntity<Pokemon> update(int id, Pokemon pokemonToUpdate)
//    {
//        for (Pokemon pokemon : pokemonList)
//        {
//            if (pokemon.getId() == id)
//            {
//                pokemon.setName(pokemonToUpdate.getName());
//                return new ResponseEntity<>(pokemon, HttpStatus.OK);
//            }
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
}
