package com.example.euphoriagifts2.model.entity;

import com.example.euphoriagifts2.model.entity.enums.RoleNameEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "gifts")
public class GiftEntity extends BaseEntity {
    private String name;

    private String description;
    private BigDecimal price;
    private CategoryEntity category;
        //   private Order order;
    private Integer quantity;
  //  private OfflineShopEntity shopEntity;
    private UserEntity userEntity;

    private PictureEntity picture;
//    private Set<PictureEntity> pictures;
    private Set<CommentEntity> comments;

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

//    @ManyToOne(optional = false)
//    public OfflineShopEntity getShopEntity() {
//        return shopEntity;
//    }
//
//    public void setShopEntity(OfflineShopEntity shopEntity) {
//        this.shopEntity = shopEntity;
//    }

    @ManyToOne(optional = false)
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
    @Column(nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    @OneToMany(mappedBy = "gift", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
//    public Set<PictureEntity> getPictures() {
//        return pictures;
//    }
//
//    public GiftEntity setPictures(Set<PictureEntity> pictures) {
//        this.pictures = pictures;
//        return this;
//    }
  //  @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(mappedBy = "giftEntity", targetEntity = CommentEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(Set<CommentEntity> comments) {
        this.comments = comments;
    }

    @OneToOne
    public PictureEntity getPicture() {
        return picture;
    }

    public void setPicture(PictureEntity picture) {
        this.picture = picture;
    }


//    public boolean isAdmin(){
//        if(RoleNameEnum.ADMIN.name().equals("ADMIN")){
//            return true;
//        }
//        return false;
//    }

}


