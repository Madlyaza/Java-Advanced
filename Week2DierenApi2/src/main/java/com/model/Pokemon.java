package com.model;

import javax.validation.constraints.*;


public class Pokemon
{
    @Size(max = 50)
    @NotBlank
    String name;

    @Positive
    int trainerId;

    @NotNull
    @Positive
    int idTag;

    public Pokemon()
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

    public int getIdTag()
    {
        return idTag;
    }

    public void setIdTag(int idTag)
    {
        this.idTag = idTag;
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
