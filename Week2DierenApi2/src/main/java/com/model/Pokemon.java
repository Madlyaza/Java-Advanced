package com.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Pokemon
{
    @Size(max = 50)
    @NotBlank
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "trainerId")
    @NotNull
    private Trainer trainer;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Trainer getTrainer()
    {
        return trainer;
    }

    public void setTrainer(Trainer trainer)
    {
        this.trainer = trainer;
    }


}
