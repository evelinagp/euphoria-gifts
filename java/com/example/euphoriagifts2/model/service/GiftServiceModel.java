package com.example.euphoriagifts2.model.service;

import com.example.euphoriagifts2.model.entity.CategoryEntity;
import com.example.euphoriagifts2.model.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class GiftServiceModel {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private CategoryEntity category;
    //   private Order order;
    private Integer quantity;
    //   private OfflineShopEntity shopEntity;
    private UserEntity userEntity;

    //    private List<PictureServiceModel> picturesBind;
    private MultipartFile picture;

    public GiftServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

//    public OfflineShopEntity getShopEntity() {
//        return shopEntity;
//    }
//
//    public void setShopEntity(OfflineShopEntity shopEntity) {
//        this.shopEntity = shopEntity;
//    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }
}

//    public List<PictureServiceModel> getPicturesBind() {
//        return picturesBind;
//    }
//
//    public void setPicturesBind(List<PictureServiceModel> picturesBind) {
//        this.picturesBind = picturesBind;
//    }


