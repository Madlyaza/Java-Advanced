package com.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Entity
public class Trainer
{
    @Size(max = 50)
    @NotBlank
    private String name;

    @Positive
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

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

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

}
