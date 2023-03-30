package com.example.euphoriagifts2.service.impl;

import com.example.euphoriagifts2.model.entity.CommentEntity;
import com.example.euphoriagifts2.model.entity.GiftEntity;
import com.example.euphoriagifts2.model.entity.OrderEntity;
import com.example.euphoriagifts2.model.entity.PictureEntity;
import com.example.euphoriagifts2.model.entity.enums.CategoryNameEnum;
import com.example.euphoriagifts2.model.service.GiftServiceModel;
import com.example.euphoriagifts2.model.view.GiftViewModel;
import com.example.euphoriagifts2.repository.CommentRepository;
import com.example.euphoriagifts2.repository.GiftRepository;
import com.example.euphoriagifts2.repository.OrderRepository;
import com.example.euphoriagifts2.service.*;
import com.example.euphoriagifts2.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GiftServiceImpl implements GiftService {
    private final GiftRepository giftRepository;
    private final UserService userService;
    private final OrderService orderService;
    private final CategoryService categoryService;
    private final PictureService pictureService;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;
    private final CommentRepository commentRepository;
    private final OrderRepository orderRepository;

    public GiftServiceImpl(GiftRepository giftRepository, UserService userService, OrderService orderService, PictureService pictureService, ModelMapper modelMapper, CategoryService categoryService, CloudinaryService cloudinaryService, CommentRepository commentRepository, OrderRepository orderRepository) {
        this.giftRepository = giftRepository;
        this.userService = userService;
        this.orderService = orderService;
        this.pictureService = pictureService;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.cloudinaryService = cloudinaryService;

        this.commentRepository = commentRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    @Override
    public void deleteGiftById(Long id) {
        GiftEntity gift = this.giftRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Gift with id: " + id + " was not found!"));

        Set<OrderEntity> orderCollection = orderRepository.findAll().stream().filter(orderEntity -> orderEntity.getGifts().contains(gift)).collect(Collectors.toSet());

        if (orderCollection.size() > 0) {
            orderCollection.forEach(orderEntity -> {
                if (orderEntity.getGifts().size() == 1) {
                    this.orderRepository.delete(orderEntity);
                } else {
                    orderEntity.getGifts().remove(gift);
                    orderEntity.setTotalPrice(orderEntity.getTotalPrice().subtract(gift.getPrice()));
                }
            });
        }
        Set<CommentEntity> commentsCollection = gift.getComments();
        if (commentsCollection.size() > 0) {
            this.commentRepository.deleteAll(commentsCollection);
        }
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

        //  gift.setUserEntity(userService.findByUsername(principal.getName()));
        gift.setQuantity(1);
        gift.setCategory(categoryService.findByCategoryNameEnum(giftServiceModel.getCategory()));

        var picture = createPictureEntity(giftServiceModel.getPicture(), giftServiceModel.getPictureTitle());

        this.pictureService.saveEntity(picture);


        gift.setPicture(picture);
        this.giftRepository.save(gift);
    }

    private PictureEntity createPictureEntity(MultipartFile file, String pictureTitle) throws IOException {
        final CloudinaryImage uploaded = this.cloudinaryService.upload(file);

        PictureEntity picture = new PictureEntity();

        picture.setPublicId(uploaded.getPublicId());
        picture.setTitle(pictureTitle);
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

        return giftsByCategoryName;
    }

    @Override
    public void updateGift(GiftServiceModel giftServiceModel) throws IOException {
        GiftEntity giftById = giftRepository.findById
                (giftServiceModel.getId()).orElseThrow();

        giftById.setDescription(giftServiceModel.getDescription());
        giftById.setPrice(giftServiceModel.getPrice());
        giftById.setName(giftServiceModel.getName());
        giftById.setCategory(categoryService.findByCategoryNameEnum(giftServiceModel.getCategory()));

        var picture = createPictureEntity(giftServiceModel.getPicture(), giftServiceModel.getPictureTitle());

        this.pictureService.saveEntity(picture);

        giftById.setPicture(picture);
        this.giftRepository.save(giftById);

    }
}

