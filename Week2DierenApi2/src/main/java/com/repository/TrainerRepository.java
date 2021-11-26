package com.repository;

import com.model.Trainer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Transactional
@Repository
public class TrainerRepository
{
    @PersistenceContext
    private EntityManager manager;

    public List<Trainer> getTrainers()
    {
        TypedQuery<Trainer> query = manager.createQuery("SELECT a FROM Trainer a", Trainer.class);
        return query.getResultList();
    }

    public List<Trainer> getTrainersByName(String name)
    {
        TypedQuery<Trainer> query = manager.createQuery("SELECT a FROM Trainer a WHERE name LIKE '%" + name + "%'", Trainer.class);
        return query.getResultList();
    }

    public Trainer getTrainerById(Integer id)
    {
        TypedQuery<Trainer> query = manager.createQuery("SELECT a FROM Trainer WHERE id = '"+ id + "'", Trainer.class);
        return query.getSingleResult();
    }
}
