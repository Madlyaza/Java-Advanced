package com.service;

import com.model.Pokemon;
import com.model.Trainer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class TrainerService
{
    private final ArrayList<Trainer> trainerList;
    private final PokemonService pokemonService;

    public TrainerService(ArrayList<Trainer> trainerList, PokemonService pokemonService)
    {
        this.trainerList = trainerList;
        this.pokemonService = pokemonService;
    }


    public ArrayList<Trainer> getTrainers()
    {
        return trainerList;
    }

    public ResponseEntity<ArrayList<Trainer>> getTrainerByName(String name)
    {
        name = name.toLowerCase();
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

    public ResponseEntity<Trainer> getTrainerById(int id)
    {
        if(id == 0)
        {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        for(Trainer trainer : trainerList)
        {
            if (trainer.getId() == id)
            {
                return new ResponseEntity<>(trainer, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Trainer> create(Trainer trainer)
    {
        Trainer newTrainer = new Trainer();
        newTrainer.setName(trainer.getName().toLowerCase());
        newTrainer.setId(trainerList.get(trainerList.size()-1).getId()+1);

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
            if(trainerList.get(i).getId() == id)
            {
                return new ResponseEntity<>(trainerList.remove(i), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Trainer> update(int id, Trainer trainerToUpdate)
    {
        for (Trainer trainer : trainerList)
        {
            if (trainer.getId() == id)
            {
                trainer.setName(trainerToUpdate.getName().toLowerCase());
                return new ResponseEntity<>(trainer, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ArrayList<Pokemon>> getTrainersPokemon(int id)
    {
        ArrayList<Pokemon> trainersPokemonList = new ArrayList<>();
//        for(Trainer trainer : trainerList)
//        {
//            if(trainer.getId() == id)
//            {
//                ArrayList<Pokemon> pokemonList = pokemonService.getPokemon();
//                for (Pokemon pokemon : pokemonList)
//                {
//                    if(pokemon.getTrainerId() == trainer.getId())
//                    {
//                        trainersPokemonList.add(pokemon);
//                    }
//                }
//            }
//        }
        if(trainersPokemonList.size() != 0)
        {
            return new ResponseEntity<>(trainersPokemonList, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
