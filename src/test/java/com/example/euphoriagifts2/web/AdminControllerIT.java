package com.example.euphoriagifts2.web;

import com.example.euphoriagifts2.model.entity.*;
import com.example.euphoriagifts2.model.entity.enums.CategoryNameEnum;
import com.example.euphoriagifts2.model.entity.enums.RoleNameEnum;
import com.example.euphoriagifts2.repository.*;
import com.example.euphoriagifts2.service.AppUser;
import com.example.euphoriagifts2.service.CloudinaryImage;
import com.example.euphoriagifts2.service.CloudinaryService;
import com.example.euphoriagifts2.service.PictureService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.BDDMockito.then;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser(username = "admin", roles = "ADMIN")
@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private GiftRepository giftRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    private UserEntity testUser;
    private UserEntity testUser2;
    private GiftEntity testGift;

    private AppUser appUser;
    private PictureEntity testPicture;
    private CategoryEntity category;
    private CloudinaryImage cloudinaryImage;
    private FileInputStream fileInputStream;
    private MultipartFile multipartFile;


    @BeforeEach
    public void setup() throws IOException {
        RoleEntity roleEntity = new RoleEntity().setName(RoleNameEnum.ADMIN);
        roleEntity = this.roleRepository.saveAndFlush(roleEntity);

        UserEntity user = new UserEntity()
                .setEmail("admin@example.com")
                .setPassword("admin")
                .setUsername("admin")
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setAge(20)
                .setRoles(Set.of(roleEntity));
        testUser = userRepository.saveAndFlush(user);

        RoleEntity roleEntity2 = new RoleEntity().setName(RoleNameEnum.USER);
        roleEntity2 = this.roleRepository.saveAndFlush(roleEntity2);

        UserEntity user2 = new UserEntity()
                .setEmail("user@example.com")
                .setPassword("admin")
                .setUsername("user")
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setAge(20)
                .setRoles(Set.of(roleEntity2));
        testUser2 = userRepository.saveAndFlush(user2);

        List<GrantedAuthority> authorities = testUser.getRoles().stream().map(r -> {
            return new SimpleGrantedAuthority("ROLE_" .concat(r.getName().name()));
        }).collect(Collectors.toList());

        this.appUser = new AppUser(user.getUsername(), user.getPassword(), authorities);

        category = new CategoryEntity();
        category.setCategoryName(CategoryNameEnum.HOME);
        category.setDescription("Description for " + CategoryNameEnum.HOME);
        category = categoryRepository.saveAndFlush(category);

        fileInputStream = new FileInputStream("src/main/resources/static/images/404.jpg");

        multipartFile = new MockMultipartFile("file", "NameOfTheFile", "multipart/form-data", fileInputStream);

        cloudinaryImage = cloudinaryService.upload(multipartFile);

        testPicture = new PictureEntity();
        testPicture.setUrl(cloudinaryImage.getUrl());
        testPicture.setTitle(multipartFile.getOriginalFilename());
        testPicture.setPublicId(cloudinaryImage.getPublicId());

        testPicture = pictureRepository.saveAndFlush(testPicture);

        testGift = new GiftEntity();
        testGift.setPrice(BigDecimal.valueOf(10.00));
        testGift.setDescription("Test gift description.");
        testGift.setQuantity(1);
        testGift.setCategory(category);
        testGift.setName("Test gift");
        testGift.setUserEntity(testUser);
        testGift.setPicture(testPicture);
        testGift.setId(100);

        testGift = giftRepository.save(testGift);

    }

    @AfterEach
    void tearDown() {
        giftRepository.deleteAll();
        userRepository.deleteAll();
        pictureRepository.deleteAll();
        roleRepository.deleteAll();
        cloudinaryService.delete(cloudinaryImage.getPublicId());
    }

    @Test
    void testAddGiftPage() throws Exception {
        mockMvc.perform(get("/pages/admins/add-gift").with(user(appUser)).
                        with(csrf()).
                        contentType(MediaType.APPLICATION_FORM_URLENCODED)).
                andExpect(status().isOk());

    }

    @Test
    void testEditGiftPage() throws Exception {

        mockMvc.perform(get("/pages/admins/edit-gift/" + testGift.getId()).with(user(appUser)).
                        with(csrf())).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("giftServiceModel"));

    }

    @Test
    void testRemoveGift() throws Exception {

        Assertions.assertEquals(1, giftRepository.count());
        mockMvc.perform(
                        get("/pages/admins/delete-gift/" + testGift.getId()).with(user(appUser))
                ).
                andExpect(status().is3xxRedirection());
        Assertions.assertEquals(0, giftRepository.count());
    }

    @Test
    void testRemoveUser() throws Exception {

        Assertions.assertEquals(2, userRepository.count());
        mockMvc.perform(
                        get("/pages/admins/delete-role/" + testUser2.getId()).with(user(appUser))
                ).
                andExpect(status().is3xxRedirection());
        Assertions.assertEquals(1, userRepository.count());
    }

    @Test
    void testManageUsersPage() throws Exception {
        mockMvc.perform(get("/pages/admins/manage-users" ).with(user(appUser)).
                        with(csrf())).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("allUsers")).
                andExpect(model().attributeExists("admin"));

    }

    @Test
    void testAddGiftFailure() throws Exception {

        mockMvc.perform(
                post("/pages/admins/gifts/add-gift").
                        with(user(appUser)).
                        param("name", "test").
                        param("price", "1").
                        param("description", "test description").
                        param("category", category.getCategoryName().name()).
                        param("picture", testPicture.getUrl()).
                        with(csrf()).
                        contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isNotFound());

        Assertions.assertEquals(1, giftRepository.count());
    }

    @Test
    void testEditGiftFailure() throws Exception {
        mockMvc.perform(
                post("/pages/admins/gifts/edit-gift").
                        with(user(appUser)).
                        param("name", "test2").
                        param("price", "2").
                        param("description", "test description").
                        param("category", category.getCategoryName().name()).
                        param("picture", testPicture.getUrl()).
                        with(csrf()).
                        contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isNotFound());

    }
}



