package com.service;

import com.model.Pokemon;
import com.model.Trainer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
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
        fillList();
    }

    public void fillList()
    {
        Trainer trainer = new Trainer();
        trainer.setName("chelsea");
        trainer.setTrainerId(1);
        ArrayList<Integer> caughtPokemonIds = new ArrayList<>();
        caughtPokemonIds.add(1);
        caughtPokemonIds.add(2);
        trainer.setCaughtPokemon(caughtPokemonIds);
        trainerList.add(trainer);

        trainer = new Trainer();
        trainer.setName("peter");
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

    public ResponseEntity<ArrayList<Trainer>> getTrainerById(int id)
    {
        if(id == 0)
        {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        ArrayList<Trainer> matchedTrainerList = new ArrayList<>();

        for(Trainer trainer : trainerList)
        {
            if (trainer.getTrainerId() == id)
            {
                matchedTrainerList.add(trainer);
                return new ResponseEntity<>(matchedTrainerList, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Trainer> create(Trainer trainer)
    {
        Trainer newTrainer = new Trainer();
        newTrainer.setName(trainer.getName().toLowerCase());
        newTrainer.setTrainerId(trainerList.get(trainerList.size()-1).getTrainerId()+1);
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

    public ResponseEntity<Trainer> update(int id, Trainer trainerToUpdate)
    {
        for (Trainer trainer : trainerList)
        {
            if (trainer.getTrainerId() == id)
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
        for(Trainer trainer : trainerList)
        {
            if(trainer.getTrainerId() == id)
            {
                ArrayList<Pokemon> pokemonList = pokemonService.getPokemon();
                for (Pokemon pokemon : pokemonList)
                {
                    if(pokemon.getTrainerId() == trainer.getTrainerId())
                    {
                        trainersPokemonList.add(pokemon);
                    }
                }
            }
        }
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
