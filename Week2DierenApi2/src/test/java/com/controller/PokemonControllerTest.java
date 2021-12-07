package com.controller;


import com.configuration.DatabaseConfigTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.print.attribute.standard.Media;

import static net.bytebuddy.matcher.ElementMatchers.is;
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
        this.mockMvc.perform(get("/pokemon").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[:1].name").value("Charmander"))
                .andExpect(jsonPath("$.[:1].id").value(1))
                .andExpect(jsonPath("$.[:1].trainer.name").value("Chelsea"));
    }

    @Test
    public void testGetPokemonByName() throws Exception
    {
        this.mockMvc.perform(get("/pokemon")
                        .param("name","Char"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(jsonPath("$.[:1].name").value("Charmander"))
                .andExpect(jsonPath("$.[:1].id").value(1))
                .andExpect(jsonPath("$.[:1].trainer.name").value("Chelsea"));
    }

    @Test
    public void testGetPokemonById() throws Exception
    {
        this.mockMvc.perform(get("/pokemon/1"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(jsonPath("$.name").value("Charmander"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.trainer.name").value("Chelsea"));
    }

    @Test
    @DirtiesContext
    public void testPostPokemon() throws Exception
    {
        this.mockMvc.perform(post("/pokemon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Charmander\",\"trainer\":{\"id\":1,\"name\":\"Chelsea\"}}"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(jsonPath("$.name").value("Charmander"))
                .andExpect(jsonPath("$.trainer.name").value("Chelsea"));
    }

    @Test
    @DirtiesContext
    public void testPutPokemon() throws Exception
    {
        this.mockMvc.perform(put("/pokemon/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Cyndaquil\",\"trainer\":{\"id\":1,\"name\":\"Chelsea\"}}"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(jsonPath("$.name").value("Cyndaquil"))
                .andExpect(jsonPath("$.trainer.name").value("Chelsea"));
    }

    @Test
    @DirtiesContext
    public void testDeletePokemon() throws Exception
    {
        this.mockMvc.perform(delete("/pokemon/10"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(jsonPath("$.name").value("Zapdos"))
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.trainer.name").value("Jane"));
    }
}
