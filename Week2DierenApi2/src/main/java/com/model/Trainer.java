package com.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.ArrayList;

public class Trainer
{
    @Size(max = 50)
    @NotBlank
    String name;

    @Positive
    int trainerId;

    ArrayList<Integer> caughtPokemon;

    public Trainer()
    {

    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getTrainerId()
    {
        return trainerId;
    }

    public void setTrainerId(int trainerId)
    {
        this.trainerId = trainerId;
    }

    public ArrayList<Integer> getCaughtPokemon()
    {
        return caughtPokemon;
    }

    public void setCaughtPokemon(ArrayList<Integer> caughtPokemon)
    {
        this.caughtPokemon = caughtPokemon;
    }
}
