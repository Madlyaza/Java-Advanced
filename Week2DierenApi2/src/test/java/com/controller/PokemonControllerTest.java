package com.controller;


import com.configuration.DatabaseConfig;
import com.configuration.DatabaseConfigTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringJUnitConfig(classes = DatabaseConfigTest.class)
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
//        MvcResult mvcResult = this.mockMvc.perform(get("/Pokemon")).andReturn();
//        List<Pokemon> pokemonList = (List<Pokemon>) mvcResult.getResponse();
//        assertEquals("Charmander", pokemonList.get(0).getName());
    }

}
