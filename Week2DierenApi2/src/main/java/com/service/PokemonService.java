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
        fillList();
    }

    public void fillList()
    {
        Pokemon pokemon = new Pokemon();
        pokemon.setName("Charmander");
        pokemon.setIdTag(1);
        pokemon.setTrainerId(1);
        pokemonList.add(pokemon);

        pokemon = new Pokemon();
        pokemon.setName("Charmander");
        pokemon.setIdTag(2);
        pokemon.setTrainerId(1);
        pokemonList.add(pokemon);

        pokemon = new Pokemon();
        pokemon.setName("Pikachu");
        pokemon.setIdTag(3);
        pokemon.setTrainerId(2);
        pokemonList.add(pokemon);

        pokemon = new Pokemon();
        pokemon.setName("Bulbasaur");
        pokemon.setIdTag(4);
        pokemon.setTrainerId(2);
        pokemonList.add(pokemon);
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
        newPokemon.setTrainerId(pokemon.getTrainerId());
        newPokemon.setIdTag(pokemonList.get(pokemonList.size()-1).getIdTag()+1);

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
            if (pokemonList.get(i).getIdTag() == id)
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
            if (pokemon.getIdTag() == id)
            {
                pokemon.setName(pokemonToUpdate.getName());
                pokemon.setTrainerId(pokemonToUpdate.getTrainerId());
                return new ResponseEntity<>(pokemon, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
