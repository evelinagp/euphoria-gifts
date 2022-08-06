package com.example.euphoriagifts2.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testShowingRegistrationPage() throws Exception {
        mockMvc.perform(get("/users/register)")).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("http://localhost/users/login"));

    }
@Test
    void testShowingLogoutPage() throws Exception {
        mockMvc.perform(get("/users/logout)")).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("http://localhost/users/login"));

    }
    @Test
    void testUserRegistration() throws Exception {
        mockMvc.perform(post("/users/register")
                .param("email", "admin@example.com")
                .param("firstName", "Admin")
                .param("LastName", "Adminov")
                .param("username", "admin")
                .param("password", "admin")
                .param("confirmPassword", "admin")
                .param("age", "18")
                .with(csrf())).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("register"));



    }
}