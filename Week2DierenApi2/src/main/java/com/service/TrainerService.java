package com.service;

import com.model.Trainer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class TrainerService
{
    ArrayList<Trainer> trainerList;

    public TrainerService()
    {
        this.trainerList = new ArrayList<>();
        fillList();
    }

    public void fillList()
    {
        Trainer trainer = new Trainer();
        trainer.setName("Chelsea");
        trainer.setTrainerId(1);
        ArrayList<Integer> caughtPokemonIds = new ArrayList<>();
        caughtPokemonIds.add(1);
        caughtPokemonIds.add(2);
        trainer.setCaughtPokemon(caughtPokemonIds);
        trainerList.add(trainer);

        trainer = new Trainer();
        trainer.setName("Peter");
        trainer.setTrainerId(2);
        caughtPokemonIds = new ArrayList<>();
        caughtPokemonIds.add(3);
        caughtPokemonIds.add(4);
        trainer.setCaughtPokemon(caughtPokemonIds);
        trainerList.add(trainer);
    }

    public ArrayList<Trainer> getTrainers()
    {
        return trainerList;
    }

    public ResponseEntity<ArrayList<Trainer>> getTrainerByName(String name)
    {
        if(name.equals(""))
        {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        ArrayList<Trainer> matchedTrainerList = new ArrayList<>();
        for(Trainer trainer : trainerList)
        {
            if (trainer.getName().contains(name))
            {
                matchedTrainerList.add(trainer);
            }
        }
        if(matchedTrainerList.size() != 0)
        {
            return new ResponseEntity<>(matchedTrainerList, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Trainer> create(Trainer trainer)
    {
        Trainer newTrainer = new Trainer();
        newTrainer.setName(trainer.getName());
        newTrainer.setTrainerId(trainer.getTrainerId());
        newTrainer.setCaughtPokemon(trainer.getCaughtPokemon());

        if (trainerList.add(newTrainer))
        {
            return new ResponseEntity<>(newTrainer, HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<Trainer> delete(int id)
    {
        for(int i = 0; i < trainerList.size(); i++)
        {
            if(trainerList.get(i).getTrainerId() == id)
            {
                return new ResponseEntity<>(trainerList.remove(i), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Trainer> update(int id, Trainer trainer)
    {
        for (Trainer value : trainerList)
        {
            if (value.getTrainerId() == id)
            {
                value.setName(trainer.getName());
                return new ResponseEntity<>(value, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
