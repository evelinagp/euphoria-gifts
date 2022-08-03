package com.example.euphoriagifts2.service.impl;

import com.example.euphoriagifts2.model.entity.CategoryEntity;
import com.example.euphoriagifts2.model.entity.GiftEntity;
import com.example.euphoriagifts2.model.entity.PictureEntity;
import com.example.euphoriagifts2.model.entity.UserEntity;
import com.example.euphoriagifts2.model.entity.enums.CategoryNameEnum;
import com.example.euphoriagifts2.model.service.GiftServiceModel;
import com.example.euphoriagifts2.model.view.GiftViewModel;
import com.example.euphoriagifts2.repository.GiftRepository;
import com.example.euphoriagifts2.repository.PictureRepository;
import com.example.euphoriagifts2.service.*;
import com.example.euphoriagifts2.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GiftServiceImpl implements GiftService {
    private final GiftRepository giftRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final PictureService pictureService;
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private CloudinaryService cloudinaryService;

    public GiftServiceImpl(GiftRepository giftRepository, UserService userService, PictureService pictureService, PictureRepository pictureRepository, ModelMapper modelMapper, CategoryService categoryService, CloudinaryService cloudinaryService) {
        this.giftRepository = giftRepository;
        this.userService = userService;
        this.pictureService = pictureService;
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public void deleteGiftById(Long id) {
        GiftEntity gift = this.giftRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Gift with id: " + id + " was not found!"));
        deletePicturesFromCloud(gift);
        this.giftRepository.deleteById(id);
    }

    private void deletePicturesFromCloud(GiftEntity gift) {
        if (gift.getPicture() != null) {
            this.cloudinaryService.delete(gift.getPicture().getPublicId());
        }

    }


    @Override
    public void addNewGift(GiftServiceModel giftServiceModel, Principal principal) throws IOException {

        GiftEntity gift = this.modelMapper.map(giftServiceModel, GiftEntity.class);

        gift.setUserEntity(userService.findByUsername(principal.getName()));
        gift.setQuantity(0);
        gift.setCategory(categoryService.findByCategoryNameEnum(giftServiceModel.getCategory().getCategoryName()));

        var picture = createPictureEntity(giftServiceModel.getPicture());

        pictureRepository.saveAndFlush(picture);

        gift.setPicture(picture);
        this.giftRepository.save(gift);
    }

    private PictureEntity createPictureEntity(MultipartFile file) throws IOException {
        final CloudinaryImage uploaded = this.cloudinaryService.upload(file);

        PictureEntity picture = new PictureEntity();

        picture.setPublicId(uploaded.getPublicId());
        picture.setTitle(file.getName());
        picture.setUrl(uploaded.getUrl());
        return picture;
    }


    @Override
    public boolean isGiftExists(String name) {
        return this.giftRepository.existsByName(name);
    }

    @Transactional
    @Override
    public List<GiftViewModel> findAllGifts() {
        return this.giftRepository.findAll().stream().map(gift -> {
            GiftViewModel giftViewModel = modelMapper.map(gift, GiftViewModel.class);
            if (gift.getPicture() == null) {
                giftViewModel.setPictureUrl("/images/default.jpg");
            } else {
                giftViewModel.setPictureUrl(gift.getPicture().getUrl());
            }
            return giftViewModel;
        }).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public GiftEntity findGiftById(Long id) {
        return this.giftRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Gift with " + id + " doesn't exist!"));
    }

//    @Override
//    public List<GiftViewModel> getAllGiftsByCategory(String category) {
//        List<GiftViewModel> listByCategoryName = this.giftRepository.findAllByCategory_CategoryName(category);
//
//        if (listByCategoryName.isEmpty()) {
//            throw new ObjectNotFoundException("They are not any added gifts in " + category + " category yet!");
//        }
//        return listByCategoryName;
//    }


    @Override
    public List<GiftViewModel> getAllGiftsByCategoryName(CategoryNameEnum homeCategory) {

        List<GiftViewModel> giftsByCategoryName = giftRepository.findAllByCategory_CategoryName(homeCategory)
                .stream().map(gift -> {
                    GiftViewModel giftViewModel = modelMapper.map(gift, GiftViewModel.class);
                    if (gift.getPicture() == null) {
                        giftViewModel.setPictureUrl("/images/default.jpg");
                    } else {
                        giftViewModel.setPictureUrl(gift.getPicture().getUrl());
                    }
                    return giftViewModel;
                }).collect(Collectors.toList());

//        if (giftsByCategoryName.isEmpty()) {
//            throw new ObjectNotFoundException("They are not any added gifts in " + homeCategory + " category yet!");
//        }
        return giftsByCategoryName;
    }

//    @Override
//    public GiftServiceModel mapGiftServiceModel(GiftServiceModel giftServiceModel1) {
//        GiftServiceModel giftServiceModel = new GiftServiceModel();
//        giftServiceModel.setName(giftServiceModel1.getName());
//        giftServiceModel.setPrice(giftServiceModel1.getPrice());
//        giftServiceModel.setDescription(giftServiceModel1.getDescription());
//        giftServiceModel.setCategory(giftServiceModel1.getCategory());
//        giftServiceModel.setPicture(giftServiceModel1.getPicture());
//
//        return giftServiceModel;
//    }

//    @Override
//    public void update(GiftServiceModel giftServiceModel) {
//        GiftEntity gift = this.modelMapper.map(giftServiceModel, GiftEntity.class);
//        this.giftRepository.save(gift);
//    }

    @Override
    public void updateGift(GiftServiceModel giftServiceModel) throws IOException {
        GiftEntity giftById = giftRepository.findById
                (giftServiceModel.getId()).orElseThrow();

//        giftById.setCategory(categoryService.findCategoryById(giftServiceModel.getCategory().getId()));
        giftById.setDescription(giftServiceModel.getDescription());
        giftById.setPrice(giftServiceModel.getPrice());
        giftById.setName(giftServiceModel.getName());
//        giftById.setCategory(categoryService.findByCategoryNameEnum(giftServiceModel.getCategory().getCategoryName()));

        var picture = createPictureEntity(giftServiceModel.getPicture());
        pictureRepository.saveAndFlush(picture);



        giftById.setPicture(picture);
        this.giftRepository.save(giftById);

    }
}

