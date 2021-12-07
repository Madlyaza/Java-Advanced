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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig(classes = DatabaseConfigTest.class)
class TrainerControllerTest
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
    void getAllTrainers() throws Exception
    {
        this.mockMvc.perform(get("/trainer"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"));
    }

    @Test
    void getTrainerByName() throws Exception
    {
        this.mockMvc.perform(get("/trainer")
                    .param("name", "chelsea"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"));
    }

    @Test
    void getTrainerById() throws Exception
    {
        this.mockMvc.perform(get("/trainer/1"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"));
    }

    @Test
    void createTrainer() throws Exception
    {
        this.mockMvc.perform(post("/trainer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Chelsea\"}"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"));
    }

    @Test
    void updateTrainer() throws Exception
    {
        this.mockMvc.perform(put("/trainer/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Kelly\"}"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"));
    }

    @Test
    void deleteTrainer() throws Exception
    {
        this.mockMvc.perform(delete("/trainer/3"))
                .andExpect(status().is4xxClientError())
                .andExpect(header().string("Content-Type", "application/json"));
    }
}