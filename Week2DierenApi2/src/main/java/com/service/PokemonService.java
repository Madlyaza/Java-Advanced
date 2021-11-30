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
            throw new MalformedInformationException("Name search cannot be empty.");
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
            throw new MalformedInformationException("Id cannot be 0");
        }

        return pokemonRepository.getPokemonById(id);
    }

    public Pokemon create(Pokemon pokemon)
    {
        return pokemonRepository.uploadPokemon(pokemon);
    }

    public Pokemon delete(int id)
    {
        if(id == 0)
        {
            throw new MalformedInformationException("Id cannot be 0");
        }
        return pokemonRepository.deletePokemon(id);
    }

    public Pokemon update(Integer id, Pokemon pokemonToUpdate)
    {
        if(id == 0)
        {
            throw new MalformedInformationException("Id cannot be 0");
        }
        return pokemonRepository.updatePokemon(pokemonToUpdate, id);
    }
}
