package com.controller;


import com.configuration.DatabaseConfig;
import com.configuration.DatabaseConfigTest;
import com.configuration.WebConfig;
import com.model.Pokemon;
import com.model.Trainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig(classes = DatabaseConfigTest.class)
public class PokemonControllerTest
{
    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager manager;

    @BeforeEach
    public void setup()
    {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webContext).build();
    }

    @Test
    public void testGetPokemon() throws Exception
    {
        this.mockMvc.perform(get("/pokemon"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"));
    }

    @Test
    public void testGetPokemonByName() throws Exception
    {
        this.mockMvc.perform(get("/pokemon").param("name","Char"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"));
    }

    @Test
    public void testGetPokemonById() throws Exception
    {
        this.mockMvc.perform(get("/pokemon/1"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"));
    }

    @Test
    public void testPostPokemon() throws Exception
    {
        this.mockMvc.perform(post("/pokemon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Charmander\",\"trainer\":{\"id\":1,\"name\":\"Chelsea\"}}"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"));
    }
}
