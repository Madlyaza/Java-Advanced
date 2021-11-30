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
        TypedQuery<Trainer> query = manager.createQuery("SELECT a FROM Trainer a WHERE name LIKE '%:name%'", Trainer.class);
        return query.setParameter(name, name).getResultList();
    }

    public Trainer getTrainerById(Integer id)
    {
        return manager.find(Trainer.class, id);
    }

    public Trainer uploadTrainer(Trainer trainer)
    {
        manager.persist(trainer);
        return manager.find(Trainer.class, trainer.getId());
    }

    public Trainer deleteTrainer(Integer id)
    {
        Trainer trainer = manager.find(Trainer.class, id);
        manager.remove(trainer);
        return trainer;
    }

    public Trainer updateTrainer(Trainer trainer, Integer id)
    {
        Trainer trainerToUpdate = manager.find(Trainer.class, id);
        trainerToUpdate.setName(trainer.getName());
        return trainerToUpdate;
    }
}
