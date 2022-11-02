package com.example.euphoriagifts2.service.impl;

import com.example.euphoriagifts2.model.entity.*;
import com.example.euphoriagifts2.model.entity.enums.CategoryNameEnum;
import com.example.euphoriagifts2.model.entity.enums.RoleNameEnum;
import com.example.euphoriagifts2.repository.GiftRepository;
import com.example.euphoriagifts2.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GiftServiceImplTest {

    @Mock
    private GiftRepository mockGiftRepo;
    private ModelMapper modelMapper = new ModelMapper();
    private GiftEntity testGift = new GiftEntity();
    private GiftService toTest;
    private UserService mockUserService;
    private CategoryService mockCategoryService;
    private PictureService mockPictureService;
    private CloudinaryService mockCloudinaryService;

    @BeforeEach
    void setUp() {
        toTest = new GiftServiceImpl(mockGiftRepo, mockUserService, mockPictureService, modelMapper, mockCategoryService, mockCloudinaryService);
        UserEntity user = new UserEntity();
        user = user.setAge(20).setUsername("admin").setFirstName("Admin").setLastName("Adminov")
                .setEmail("admin@abv.bg").setPassword("123").setRoles(Set.of(new RoleEntity().setName(RoleNameEnum.ADMIN)));

        CategoryEntity category = new CategoryEntity();
        category.setCategoryName(CategoryNameEnum.HOME);
        category.setDescription("Description for " + CategoryNameEnum.HOME);

        PictureEntity picture = new PictureEntity();

        picture = picture.setPublicId("AshtraySceleton_sq5oum").
                setTitle("AshtraySceleton").
                setUrl("https://res.cloudinary.com/ddktqessk/image/upload/v1658338284/Home/Ashtrays/AshtraySceleton_sq5oum.jpg");

        testGift = new GiftEntity();
        testGift.setPrice(BigDecimal.valueOf(10.00));
        testGift.setDescription("Test gift description.");
        testGift.setQuantity(1);
        testGift.setCategory(category);
        testGift.setName("Test gift");
        testGift.setUserEntity(user);
        testGift.setPicture(picture);
        testGift.setId(100);
    }

    @Test
    public void findAll() {
        toTest.findAllGifts();
        verify(mockGiftRepo).findAll();
    }

    @Test
    public void testGetAllGiftsByCategoryName() {
        toTest.getAllGiftsByCategoryName(CategoryNameEnum.WOMEN);
        verify(mockGiftRepo).findAllByCategory_CategoryName(CategoryNameEnum.WOMEN);
    }

    @Test
    public void testIsGiftExists() {
        toTest.isGiftExists("Rose Candle");
        verify(mockGiftRepo).existsByName("Rose Candle");

    }

    @Test
    public void testFindById() {
        when(mockGiftRepo.findById(testGift.getId()))
                .thenReturn(Optional.of(testGift));

        GiftEntity actual = toTest.findGiftById(testGift.getId());

        Assertions.assertEquals(actual.getName(), testGift.getName());

    }

}

