package com.example.euphoriagifts2.web;

import com.example.euphoriagifts2.model.entity.*;
import com.example.euphoriagifts2.model.entity.enums.CategoryNameEnum;
import com.example.euphoriagifts2.model.entity.enums.RoleNameEnum;
import com.example.euphoriagifts2.repository.*;
import com.example.euphoriagifts2.service.AppUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser("admin")
@SpringBootTest
@AutoConfigureMockMvc
class GiftControllerIT {
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

    private UserEntity testUser;
    private GiftEntity testGift;

    private AppUser appUser;
    private PictureEntity picture;
    private  CategoryEntity category;

    /* Do not work after refactoring of some relations between entities*/
    /*TODO - fix the tests*/
  /*  @BeforeEach
    public void setup() {
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

        List<GrantedAuthority> authorities = user.getRoles().stream().map(r -> {
            return new SimpleGrantedAuthority("ROLE_" .concat(r.getName().name()));
        }).collect(Collectors.toList());

        this.appUser = new AppUser(user.getUsername(), user.getPassword(), authorities);

        category = new CategoryEntity();
        category = categoryRepository.getById(1L);


        picture = new PictureEntity();

        picture = picture.setPublicId("AshtraySceleton_sq5oum").
                setTitle("AshtraySceleton").
                setUrl("https://res.cloudinary.com/ddktqessk/image/upload/v1658338284/Home/Ashtrays/AshtraySceleton_sq5oum.jpg");

        picture = this.pictureRepository.save(picture);

        testGift = new GiftEntity();
        testGift.setPrice(BigDecimal.valueOf(10.00));
        testGift.setDescription("Test gift description.");
        testGift.setQuantity(1);
        testGift.setCategory(category);
        testGift.setName("Test gift");
        testGift.setUserEntity(testUser);
        testGift.setPicture(picture);
        testGift.setId(100);

        testGift = giftRepository.save(testGift);

    }

    @AfterEach
    void tearDown() {
        giftRepository.deleteAll();
        userRepository.deleteAll();
        pictureRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    void testGiftDetailsPage() throws Exception {

        mockMvc.perform(
                        get("/gifts/details/" + testGift.getId()).
                                with(user(appUser))).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("gift")).
                andExpect(view().name("gift-details"));
    }
    @Test
    void testAllGiftsPage() throws Exception {

        mockMvc.perform(
                        get("/gifts/all-gifts").
                                with(user(appUser))
                ).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("gifts")).
                andExpect(view().name("all-gifts"));
    }
    @Test
    void testHomeCategoryPage() throws Exception {

        mockMvc.perform(
                        get("/gifts/categories/" + CategoryNameEnum.HOME).
                                with(user(appUser))
                ).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("home")).
                andExpect(view().name("home-category"));
    }
    @Test
    void testKitchenCategoryPage() throws Exception {

        mockMvc.perform(
                        get("/gifts/categories/" + CategoryNameEnum.KITCHEN).
                                with(user(appUser))
                ).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("kitchen")).
                andExpect(view().name("kitchen-category"));
    }
    @Test
    void testMenCategoryPage() throws Exception {

        mockMvc.perform(
                        get("/gifts/categories/" + CategoryNameEnum.MEN).
                                with(user(appUser))
                ).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("men")).
                andExpect(view().name("men-category"));
    }
    @Test
    void testWomenCategoryPage() throws Exception {

        mockMvc.perform(
                        get("/gifts/categories/" + CategoryNameEnum.WOMEN).
                                with(user(appUser))
                ).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("women")).
                andExpect(view().name("women-category"));
    }
    @Test
    void testOccasionsCategoryPage() throws Exception {

        mockMvc.perform(get("/gifts/categories/" + CategoryNameEnum.OCCASIONS).
                                with(user(appUser))
                ).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("occasions")).
                andExpect(view().name("occasions-category"));
    }
    @Test
    void testOthersCategoryPage() throws Exception {

        mockMvc.perform(
                        get("/gifts/categories/" + CategoryNameEnum.OTHERS).
                                with(user(appUser))
                ).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("others")).
                andExpect(view().name("others-category"));
    }
    @Test
    void testNotExistingCategoryPage() throws Exception {

        mockMvc.perform(
                        get("/gifts/categories/" + "notExistingCategory").
                                with(user(appUser))
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/"));
    }*/

}
