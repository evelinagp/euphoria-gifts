package com.example.euphoriagifts2.model.binding;

import com.example.euphoriagifts2.annotations.ValidUrl;
import com.example.euphoriagifts2.model.entity.enums.CategoryNameEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.math.BigDecimal;

public class
GiftAddBindingModel {
    private String name;
    private BigDecimal price;
    private String description;
    private CategoryNameEnum category;

    @ValidUrl
    private MultipartFile picture;

    private String pictureTitle;

    public GiftAddBindingModel() {
    }
    @NotBlank(message = "Name can not be empty string!")
    @Size(min = 3, max = 40, message = "Name length must be between 3 and 40 characters!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @NotNull(message = "Price can not be empty field!")
    @Positive(message = "Price must be positive!")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotNull(message = "Category can not be empty field!")
    @Enumerated(EnumType.STRING)
        public CategoryNameEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryNameEnum category) {
        this.category = category;
    }

    @NotBlank(message = "Description can not be empty field!")
    @Size(min = 15, max = 500, message = "Description length must be between 15 and 500 characters!")
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

    @NotBlank(message = "Picture title can not be empty field!")
    @Size(min = 3, max = 20, message = "Picture title length must be between 3 and 20 characters!")
    public String getPictureTitle() {
        return pictureTitle;
    }

    public void setPictureTitle(String pictureTitle) {
        this.pictureTitle = pictureTitle;
    }
}
