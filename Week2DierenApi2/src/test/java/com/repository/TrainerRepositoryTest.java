package com.repository;

import com.configuration.DatabaseConfigTest;
import com.model.Trainer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DatabaseConfigTest.class)
@Transactional
class TrainerRepositoryTest
{
    @Autowired
    private TrainerRepository trainerRepository;

    @PersistenceContext
    private EntityManager manager;

    @Test
    void getTrainers()
    {
        List<Trainer> trainerList = trainerRepository.getTrainers();
        assertEquals(4, trainerList.size());
        assertEquals("Chelsea", trainerList.get(0).getName());
        assertEquals("Jane", trainerList.get(3).getName());
    }

    @Test
    void getTrainersByName()
    {
        List<Trainer> trainerList = trainerRepository.getTrainersByName("Chel");
        assertEquals(1, trainerList.size());
        trainerList = trainerRepository.getTrainersByName("e");
        assertEquals(4, trainerList.size());
        assertEquals("Chelsea", trainerList.get(0).getName());
    }

    @Test
    void getTrainerById()
    {
        Trainer trainer = trainerRepository.getTrainerById(1);
        assertEquals("Chelsea", trainer.getName());
    }

    @Test
    void uploadTrainer()
    {
        Trainer trainer = new Trainer();
        trainer.setName("Sophie");
        Trainer uploadedTrainer = trainerRepository.uploadTrainer(trainer);

        assertEquals("Sophie", uploadedTrainer.getName());
        assertEquals(5, uploadedTrainer.getId());
    }

    @Test
    void deleteTrainer()
    {
        Trainer trainer = trainerRepository.deleteTrainer(4);
        assertEquals("Jane", trainer.getName());
    }

    @Test
    void updateTrainer()
    {
        Trainer trainer = new Trainer();
        trainer.setName("Sophie");
        Trainer uploadedTrainer = trainerRepository.updateTrainer(trainer,2);

        assertEquals("Sophie", manager.find(Trainer.class, 2).getName());
    }
}