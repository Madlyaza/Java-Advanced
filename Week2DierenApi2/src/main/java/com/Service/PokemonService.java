package com.Service;

import com.Model.Pokemon;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public class PokemonService
{
    ArrayList<Pokemon> pokemonList;

    public PokemonService()
    {
        this.pokemonList = new ArrayList<>();
        fillList();
    }

    public void fillList()
    {
        Pokemon pokemon = new Pokemon();
        pokemon.setName("Charmander");
        pokemon.setIdTag(2);
        pokemon.setTrainerId(161);
        pokemonList.add(pokemon);

        Pokemon pokemon2 = new Pokemon();
        pokemon2.setName("Squirtle");
        pokemon2.setIdTag(1);
        pokemon2.setTrainerId(51);
        pokemonList.add(pokemon2);
    }

    public ArrayList<Pokemon> getPokemon()
    {
        return pokemonList;
    }

    public ResponseEntity<Pokemon> getPokemonByName(String name)
    {
        for (Pokemon pokemon: pokemonList)
        {
            if (pokemon.getName().equals(name))
            {
                return new ResponseEntity<>(pokemon, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Pokemon> create(Pokemon pokemon)
    {
        if(pokemonList.add(pokemon))
        {
            return new ResponseEntity<>(pokemon, HttpStatus.CREATED);
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
        for (int i = 0; i < pokemonList.size(); i++)
        {
            if(pokemonList.get(i).getIdTag() == id)
            {
                pokemonList.get(i).setName(pokemonToUpdate.getName());
                pokemonList.get(i).setTrainerId(pokemonToUpdate.getTrainerId());
                return new ResponseEntity<>(pokemonList.get(i), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
