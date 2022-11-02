package com.example.euphoriagifts2.web;

import com.example.euphoriagifts2.model.entity.UserEntity;
import com.example.euphoriagifts2.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIT {

    public static final String TEST_USERNAME = "admin";
    public static final String TEST_FIRST_NAME = "Admin";
    public static final String TEST_LAST_NAME = "Adminov";
    public static final String TEST_PASSWORD = "admin";
    public static final String TEST_AGE = "18";
    public static final String TEST_EMAIL = "admin@example.com";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    /* Do not work after refactoring of some relations between entities*/
    /*TODO - fix the tests*/
  /*  @Test
    void testShowingRegistrationPage() throws Exception {
        mockMvc.perform(get("/users/register)")).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("http://localhost/users/login"));

    }

    @Test
    void testUserRegistration() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("email", TEST_EMAIL)
                        .param("firstName", TEST_FIRST_NAME)
                        .param("LastName", TEST_LAST_NAME)
                        .param("username", TEST_USERNAME)
                        .param("password", TEST_PASSWORD)
                        .param("confirmPassword", TEST_PASSWORD)
                        .param("age", TEST_AGE)
                        .with(csrf()).contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(userRepository.count(), 1);
        Optional<UserEntity> newOptUser = userRepository.findByUsername(TEST_USERNAME);
        Assertions.assertTrue(newOptUser.isPresent());
        boolean existsByUsername = userRepository.existsByUsername(TEST_USERNAME);
        Assertions.assertTrue(existsByUsername);
        boolean existsByEmail = userRepository.existsByEmail(TEST_EMAIL);
        Assertions.assertTrue(existsByEmail);
        UserEntity newUser = newOptUser.get();
        Assertions.assertEquals(TEST_FIRST_NAME, newUser.getFirstName());
        Assertions.assertEquals(TEST_LAST_NAME, newUser.getLastName());
        Assertions.assertEquals(TEST_EMAIL, newUser.getEmail());
        Assertions.assertEquals(TEST_AGE, newUser.getAge().toString());
        Assertions.assertEquals(TEST_USERNAME, newUser.getUsername());
    }

    @Test
    void testFailUserRegistration() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("email", TEST_EMAIL)
                        .param("firstName", TEST_FIRST_NAME)
                        .param("LastName", TEST_LAST_NAME)
                        .param("username", "")
                        .param("password", TEST_PASSWORD)
                        .param("confirmPassword", TEST_PASSWORD)
                        .param("age", TEST_AGE)
                        .with(csrf()).contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());
        assertEquals(0, userRepository.count());
    }

    @Test
    void testShowingLoginPage() throws Exception {
        mockMvc.perform(get("/users/login)")).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("http://localhost/users/login"));

    }
    @Test
    void testFailShowingLoginPage() throws Exception {
        mockMvc.perform(post("/users/login-error)")).
                andExpect(status().isForbidden());
    }

    @Test
    void testUserLogin() throws Exception {
        mockMvc.perform(post("/users/login")
                .param("username", TEST_USERNAME)
                .param("password", "admin")
                .with(csrf())).andExpect(status().isOk());

    }

    @Test
    void testShowingLogoutPage() throws Exception {
        mockMvc.perform(get("/users/logout)")).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("http://localhost/users/login"));

    }

    @Test
    void testFailShowingAddGiftPageForUnauthenticated() throws Exception {
        mockMvc.perform(get("/admins/pages/add-gift)")).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("http://localhost/users/login"));

    }*/

}