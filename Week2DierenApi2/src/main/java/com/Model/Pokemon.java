package com.Model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class Pokemon
{
    @Size(max = 50)
    @NotBlank
    String name;

    @PositiveOrZero
    int trainerId;

    @NotNull
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
