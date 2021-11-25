package com.repository;

import com.model.Pokemon;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        TypedQuery<Pokemon> query = manager.createQuery("SELECT a FROM Pokemon a WHERE name LIKE '%" + name + "%'", Pokemon.class);
        return query.getResultList();
    }

    public Pokemon getPokemonById(Integer id)
    {
        TypedQuery<Pokemon> query = manager.createQuery("SELECT a FROM Pokemon a WHERE id = '"+id+"'", Pokemon.class);
        return query.getSingleResult();
    }
}
