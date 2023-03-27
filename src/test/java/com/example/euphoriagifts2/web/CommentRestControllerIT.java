package com.example.euphoriagifts2.web;

import com.example.euphoriagifts2.model.binding.CreateCommentBidingModel;
import com.example.euphoriagifts2.model.entity.*;
import com.example.euphoriagifts2.model.entity.enums.CategoryNameEnum;
import com.example.euphoriagifts2.model.entity.enums.RoleNameEnum;
import com.example.euphoriagifts2.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser("example")
@SpringBootTest
@AutoConfigureMockMvc
class CommentRestControllerIT {

    public static final String COMMENT_1 = "Test comment 1";
    public static final String COMMENT_2 = "Test comment 2";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GiftRepository giftRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RoleRepository roleRepository;

    private UserEntity testUser;
    private GiftEntity testGift;
    private PictureEntity testPicture;
    private CategoryEntity testCategory;

/* Do not work after refactoring of some relations between entities*/
   /*TODO - fix the tests*/
  /*   @BeforeEach
    void setUp() {
        RoleEntity testRole = new RoleEntity().setName(RoleNameEnum.ADMIN);
        testRole = this.roleRepository.save(testRole);
        testUser = new UserEntity();
        testUser.setEmail("example@example.com")
                .setPassword("admin")
                .setUsername("example")
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setAge(20)
                .setRoles(Set.of(testRole));

        testUser = userRepository.save(testUser);

        testCategory = new CategoryEntity();
        testCategory.setCategoryName(CategoryNameEnum.HOME);
        testCategory.setDescription("Description for " + CategoryNameEnum.HOME);

        this.categoryRepository.save(testCategory);

        testPicture = new PictureEntity();

        testPicture = testPicture.setPublicId("AshtraySceleton_sq5oum").
                setTitle("AshtraySceleton").
                setUrl("https://res.cloudinary.com/ddktqessk/image/upload/v1658338284/Home/Ashtrays/AshtraySceleton_sq5oum.jpg");

        this.pictureRepository.save(testPicture);


    }

    @AfterEach
    void destroy() {
        giftRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void testGetComments() throws Exception {
        var gift = initComments(initGift());
        mockMvc.perform(get("/api/" + gift.getId() + "/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

    }

    @Test
    void testCreateComments() throws Exception {
        CreateCommentBidingModel testComment = new CreateCommentBidingModel()
                .setMessage(COMMENT_1);

        var emptyGift = initGift();
        mockMvc.perform(post("/api/" + emptyGift.getId() + "/comments").
                        contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(testComment))
                        .accept(MediaType.APPLICATION_JSON).with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", MatchesPattern.matchesPattern("/api/" + emptyGift.getId() + "/comments/\\d")))
                .andExpect(jsonPath("$.message").value(is(COMMENT_1)));


    }

    private GiftEntity initGift() {
        testGift = new GiftEntity();
        testGift.setPrice(BigDecimal.valueOf(10.00));
        testGift.setDescription("Test gift description.");
        testGift.setQuantity(1);
        testGift.setCategory(testCategory);
        testGift.setName("Test gift");
        testGift.setUserEntity(testUser);
        testGift.setPicture(testPicture);
        testGift.setId(100);

        return giftRepository.save(testGift);
    }

    private GiftEntity initComments(GiftEntity gift) {
        CommentEntity comment1 = new CommentEntity();
        comment1.setCreatedOn(LocalDateTime.now());
        comment1.setGiftEntity(gift);
        comment1.setIsApproved(true);
        comment1.setMessage(COMMENT_1);
        comment1.setUser(testUser);

        CommentEntity comment2 = new CommentEntity();
        comment2.setCreatedOn(LocalDateTime.now());
        comment2.setGiftEntity(gift);
        comment2.setIsApproved(true);
        comment2.setMessage(COMMENT_2);
        comment2.setUser(testUser);

        gift.setComments(Set.of(comment1, comment2));

        return giftRepository.save(gift);
    }
*/
}