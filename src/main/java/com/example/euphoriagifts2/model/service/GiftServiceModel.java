package com.example.euphoriagifts2.model.service;

import com.example.euphoriagifts2.model.entity.CategoryEntity;
import com.example.euphoriagifts2.model.entity.UserEntity;
import com.example.euphoriagifts2.model.entity.enums.CategoryNameEnum;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class GiftServiceModel {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private CategoryNameEnum category;
    private Integer quantity;

    private UserEntity userEntity;

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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
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

    public CategoryNameEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryNameEnum category) {
        this.category = category;
    }
}



