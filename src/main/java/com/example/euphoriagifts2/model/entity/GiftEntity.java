package com.example.euphoriagifts2.model.entity;

import com.example.euphoriagifts2.model.entity.enums.RoleNameEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "gifts")
public class GiftEntity extends BaseEntity {
    private String name;

    private String description;
    private BigDecimal price;
    private CategoryEntity category;
    private Integer quantity;

    //private OrderEntity orderEntity;
    private UserEntity userEntity;

    private PictureEntity picture;
    private Set<CommentEntity> comments;

    public GiftEntity() {
      this.comments = new LinkedHashSet<>();
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

    @ManyToOne(fetch = FetchType.EAGER)
    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    @Column(nullable = false)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @ManyToOne(optional = false)
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Lob
    @Column(nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //@OneToMany()
    @OneToMany(mappedBy = "giftEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(Set<CommentEntity> comments) {
        this.comments = comments;
    }

    @OneToOne(optional = false)
    public PictureEntity getPicture() {
        return picture;
    }

    public void setPicture(PictureEntity picture) {
        this.picture = picture;
    }


    //    public OrderEntity getOrderEntity() {
//        return orderEntity;
//    }
//
//    public void setOrderEntity(OrderEntity orderEntity) {
//        this.orderEntity = orderEntity;
//    }

}


