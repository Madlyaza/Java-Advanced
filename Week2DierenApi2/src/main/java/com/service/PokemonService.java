package com.service;

import com.exception.DataNotFoundException;
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
//        newPokemon.setId(pokemonList.get(pokemonList.size()-1).getId()+1);
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
//    public ResponseEntity<Pokemon> delete(int id)
//    {
//        for (int i = 0; i < pokemonList.size(); i++)
//        {
//            if (pokemonList.get(i).getId() == id)
//            {
//                return new ResponseEntity<>(pokemonList.remove(i), HttpStatus.OK);
//            }
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
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
