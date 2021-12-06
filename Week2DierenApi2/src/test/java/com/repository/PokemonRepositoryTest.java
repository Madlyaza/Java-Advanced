package com.repository;

import com.configuration.DatabaseConfigTest;
import com.model.Pokemon;
import com.model.Trainer;
import com.repository.PokemonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.logging.ConsoleHandler;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DatabaseConfigTest.class)
@Transactional
class PokemonRepositoryTest
{
    @Autowired
    private PokemonRepository pokemonRepository;

    @PersistenceContext
    private EntityManager manager;

    @Test
    void getPokemon()
    {
        List<Pokemon> pokemonList = pokemonRepository.getPokemon();
        assertEquals(10, pokemonList.size());
        assertEquals("Charmander", pokemonList.get(0).getName());
        assertEquals("Zapdos", pokemonList.get(9).getName());
    }

    @Test
    void getPokemonByName()
    {
        List<Pokemon> pokemonList = pokemonRepository.getPokemonByName("Char");
        assertEquals(2, pokemonList.size());
        assertEquals("Charmander", pokemonList.get(0).getName());
        assertEquals("Charizard", pokemonList.get(1).getName());
    }

    @Test
    void getPokemonById()
    {
        Pokemon pokemon = pokemonRepository.getPokemonById(1);
        assertEquals("Charmander" ,pokemon.getName());
    }

    @Test
    void uploadPokemon()
    {
        Pokemon pokemon = new Pokemon();
        pokemon.setName("Bulbasaur");
        pokemon.setTrainer(manager.find(Trainer.class, 1));
        Pokemon uploadedPokemon = pokemonRepository.uploadPokemon(pokemon);

        assertEquals("Bulbasaur", uploadedPokemon.getName());
        assertEquals("Chelsea", uploadedPokemon.getTrainer().getName());
    }

    @Test
    void deletePokemon()
    {
        Pokemon deletedPokemon = pokemonRepository.deletePokemon(1);
        assertEquals("Charmander", deletedPokemon.getName());
    }

    @Test
    void updatePokemon()
    {
        Pokemon pokemon = new Pokemon();
        pokemon.setName("Bulbasaur");
        pokemon.setTrainer(manager.find(Trainer.class, 1));
        Pokemon uploadedPokemon = pokemonRepository.updatePokemon(pokemon, 1);

        assertEquals("Bulbasaur", manager.find(Pokemon.class, 1).getName());
        assertEquals("Chelsea", manager.find(Pokemon.class, 1).getTrainer().getName());
    }
}