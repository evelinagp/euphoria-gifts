package com.example.euphoriagifts2.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void indexPageTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is(200));
    }


    @Test
    public void aboutPageTest() throws Exception {
        mockMvc.perform(get("/about"))
                .andExpect(status().is(200));
    }
    @Test
    public void contactsPageTest() throws Exception {
        mockMvc.perform(get("/contacts"))
                .andExpect(status().is(200));
    }
}