package com.repository;

import com.model.Pokemon;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PokemonRepository
{
    @PersistenceContext
    private EntityManager manager;

    public List<Pokemon> getPokemon()
    {
        TypedQuery<Pokemon> query = manager.createQuery("SELECT a FROM Pokemon a", Pokemon.class);
        return query.getResultList();
    }
}
