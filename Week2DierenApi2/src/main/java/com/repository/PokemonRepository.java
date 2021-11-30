package com.repository;

import com.model.Pokemon;
import com.model.Trainer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class PokemonRepository
{
    @PersistenceContext
    private EntityManager manager;

    public List<Pokemon> getPokemon()
    {
        TypedQuery<Pokemon> query = manager.createQuery("SELECT a FROM Pokemon a", Pokemon.class);
        return query.getResultList();
    }

    public List<Pokemon> getPokemonByName(String name)
    {
        TypedQuery<Pokemon> query = manager.createQuery("SELECT a FROM Pokemon a WHERE name LIKE '%:name%'", Pokemon.class);
        query.setParameter(name, name);
        return query.getResultList();
    }

    public Pokemon getPokemonById(Integer id)
    {
        return manager.find(Pokemon.class, id);
    }

    public Pokemon uploadPokemon(Pokemon pokemon)
    {
        manager.persist(pokemon);
        return manager.find(Pokemon.class, pokemon.getId());
    }

    public Pokemon deletePokemon(Integer id)
    {
        Pokemon pokemon = manager.find(Pokemon.class, id);
        manager.remove(pokemon);
        return pokemon;
    }

    public Pokemon updatePokemon(Pokemon pokemon, Integer id)
    {
        Pokemon pokemonToUpdate = manager.find(Pokemon.class, id);
        pokemonToUpdate.setName(pokemon.getName());
        return pokemonToUpdate;
    }
}
