package com.example.euphoriagifts2.web;

import com.example.euphoriagifts2.model.entity.CommentEntity;
import com.example.euphoriagifts2.model.entity.GiftEntity;
import com.example.euphoriagifts2.model.entity.RoleEntity;
import com.example.euphoriagifts2.model.entity.UserEntity;
import com.example.euphoriagifts2.model.entity.enums.CategoryNameEnum;
import com.example.euphoriagifts2.model.entity.enums.RoleNameEnum;
import com.example.euphoriagifts2.repository.CommentRepository;
import com.example.euphoriagifts2.repository.GiftRepository;
import com.example.euphoriagifts2.repository.RoleRepository;
import com.example.euphoriagifts2.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
class CommentRestControllerIT {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private  GiftRepository giftRepository;

    @Autowired
    private  CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private UserEntity testUser;

    @BeforeEach
    void setUp(){
        RoleEntity testRole = new RoleEntity().setName(RoleNameEnum.ADMIN);
        testRole = this.roleRepository.save(testRole);
        testUser = new UserEntity();
        testUser.setEmail("admin@example.com")
                .setPassword("admin")
                .setUsername("admin")
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setAge(20)
                .setRoles(Set.of(testRole));

        testUser = userRepository.save(testUser);

    }
    @AfterEach
    void destroy(){
        userRepository.deleteAll();
        giftRepository.deleteAll();
    }

//    @Test
//    void testGetComments(){
//    long giftId = initGift();
//    }
//    private long initGift(){
//        GiftEntity testGift = new GiftEntity();
//        testGift.setName("testing gift").setDescription("fffrefxer");
//        testGift.setPrice(BigDecimal.valueOf(5));
//        testGift.setQuantity(1);
//
//
//
//        testGift = giftRepository.save(testGift);
//
//        CommentEntity comment1 = new CommentEntity();
//        comment1.setCreatedOn(LocalDateTime.now());
//        comment1.setGiftEntity(testGift);
//        comment1.setIsApproved(true);
//        comment1.setMessage("Test comment 1");
//        comment1.setUser(testUser);
//
//        CommentEntity comment2 = new CommentEntity();
//        comment2.setCreatedOn(LocalDateTime.now());
//        comment2.setGiftEntity(testGift);
//        comment2.setIsApproved(true);
//        comment2.setMessage("Test comment 2");
//        comment2.setUser(testUser);
//
//        testGift.setComments(Set.of(comment1, comment2));
//
//        return giftRepository.save(testGift).getId();
//    }

}