package com.Model;

public class Trainer
{
    String name;
    int trainerId;

    public Trainer(String name, int trainerId)
    {
        this.name = name;
        this.trainerId = trainerId;
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
}
