package com.service;

import com.exception.DataNotFoundException;
import com.exception.MalformedInformationException;
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

    public Trainer create(Trainer trainer)
    {
        return trainerRepository.uploadTrainer(trainer);
    }

    public Trainer delete(Integer id)
    {
        if(id == 0)
        {
            throw new MalformedInformationException("id cannot be 0");
        }
        return trainerRepository.deleteTrainer(id);
    }

    public Trainer update(Integer id, Trainer trainerToUpdate)
    {
        if(id == 0)
        {
            throw new MalformedInformationException("Id cannot be 0");
        }
        return trainerRepository.updateTrainer(trainerToUpdate, id);
    }

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
