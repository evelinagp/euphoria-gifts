package com.example.euphoriagifts2.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "gifts")
public class GiftEntity extends BaseEntity {
    private String name;
    private BigDecimal price;
    private Set<CategoryEntity> categories;
        //   private Order order;
    private Integer quantity;
    private OfflineShopEntity shopEntity;
    private UserEntity userEntity;

    public GiftEntity() {
    }

    @Column(unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public GiftEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
    }


    @Column(nullable = false)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @ManyToOne(optional = false)
    public OfflineShopEntity getShopEntity() {
        return shopEntity;
    }

    public void setShopEntity(OfflineShopEntity shopEntity) {
        this.shopEntity = shopEntity;
    }

    @ManyToOne(optional = false)
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}


