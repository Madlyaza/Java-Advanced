package com.service;

import com.exception.DataNotFoundException;
import com.model.Pokemon;
import com.model.Trainer;
import com.repository.PokemonRepository;
import com.repository.TrainerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TrainerService
{
    private final TrainerRepository trainerRepository;
    private final PokemonRepository pokemonRepository;

    public TrainerService(TrainerRepository trainerRepository, PokemonRepository pokemonRepository)
    {
        this.trainerRepository = trainerRepository;
        this.pokemonRepository = pokemonRepository;
    }


    public List<Trainer> getTrainers()
    {
        return trainerRepository.getTrainers();
    }

    public List<Trainer> getTrainerByName(String name)
    {
        if(name.equals(""))
        {
            throw new DataNotFoundException("Name cannot be empty");
        }

        List<Trainer> matchedTrainerList = new ArrayList<>(trainerRepository.getTrainersByName(name));

        if(matchedTrainerList.size() != 0)
        {
            throw new DataNotFoundException("No trainers by that name");
        }
        return matchedTrainerList;

    }

    public Trainer getTrainerById(Integer id)
    {
        if(id == 0)
        {
            throw new DataNotFoundException("id cannot be 0");
        }

        return trainerRepository.getTrainerById(id);
    }
//
//    public ResponseEntity<Trainer> create(Trainer trainer)
//    {
//        Trainer newTrainer = new Trainer();
//        newTrainer.setName(trainer.getName().toLowerCase());
//        newTrainer.setId(trainerList.get(trainerList.size()-1).getId()+1);
//
//        if (trainerList.add(newTrainer))
//        {
//            return new ResponseEntity<>(newTrainer, HttpStatus.CREATED);
//        }
//        else
//        {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//    }
//
//    public ResponseEntity<Trainer> delete(int id)
//    {
//        for(int i = 0; i < trainerList.size(); i++)
//        {
//            if(trainerList.get(i).getId() == id)
//            {
//                return new ResponseEntity<>(trainerList.remove(i), HttpStatus.OK);
//            }
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    public ResponseEntity<Trainer> update(int id, Trainer trainerToUpdate)
//    {
//        for (Trainer trainer : trainerList)
//        {
//            if (trainer.getId() == id)
//            {
//                trainer.setName(trainerToUpdate.getName().toLowerCase());
//                return new ResponseEntity<>(trainer, HttpStatus.OK);
//            }
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    public ResponseEntity<ArrayList<Pokemon>> getTrainersPokemon(int id)
//    {
//        ArrayList<Pokemon> trainersPokemonList = new ArrayList<>();
////        for(Trainer trainer : trainerList)
////        {
////            if(trainer.getId() == id)
////            {
////                ArrayList<Pokemon> pokemonList = pokemonService.getPokemon();
////                for (Pokemon pokemon : pokemonList)
////                {
////                    if(pokemon.getTrainerId() == trainer.getId())
////                    {
////                        trainersPokemonList.add(pokemon);
////                    }
////                }
////            }
////        }
//        if(trainersPokemonList.size() != 0)
//        {
//            return new ResponseEntity<>(trainersPokemonList, HttpStatus.OK);
//        }
//        else
//        {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
