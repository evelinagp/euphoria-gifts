package com.example.euphoriagifts2.model.binding;

import com.example.euphoriagifts2.model.entity.CategoryEntity;
import com.example.euphoriagifts2.model.entity.OfflineShopEntity;
import com.example.euphoriagifts2.model.entity.UserEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

public class GiftAddBindingModel {
    private String name;
    private BigDecimal price;
    private Set<CategoryEntity> categories;
    //   private Order order;
    private Integer quantity;
    private OfflineShopEntity shopEntity;
    private UserEntity userEntity;

    public GiftAddBindingModel() {
    }
    @NotBlank(message = "Name can not be empty string.")
    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters.")
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

    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public OfflineShopEntity getShopEntity() {
        return shopEntity;
    }

    public void setShopEntity(OfflineShopEntity shopEntity) {
        this.shopEntity = shopEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
