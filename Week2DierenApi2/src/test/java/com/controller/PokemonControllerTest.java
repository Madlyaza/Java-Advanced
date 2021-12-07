package com.controller;


import com.configuration.DatabaseConfigTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.print.attribute.standard.Media;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(classes = DatabaseConfigTest.class)
public class PokemonControllerTest
{
    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

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
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value());
    }

    @Test
    public void testGetPokemonByName() throws Exception
    {
        this.mockMvc.perform(get("/pokemon")
                        .param("name","Char"))
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

    @Test
    public void testPutPokemon() throws Exception
    {
        this.mockMvc.perform(put("/pokemon/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Cyndaquil\",\"trainer\":{\"id\":1,\"name\":\"Chelsea\"}}"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"));
    }

    @Test
    public void testDeletePokemon() throws Exception
    {
        this.mockMvc.perform(delete("/pokemon/10"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"));
    }
}
