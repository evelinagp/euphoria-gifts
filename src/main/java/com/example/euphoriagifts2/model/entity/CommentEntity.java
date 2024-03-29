package com.example.euphoriagifts2.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity {

    private String message;
    private LocalDateTime createdOn;
    private boolean isApproved;
    private UserEntity user;
    private GiftEntity giftEntity;


    public CommentEntity() {
    }
    @Column(nullable = false)
    public boolean isIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean approved) {
        this.isApproved = approved;
    }

    @Lob
    @Column(nullable = false)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Column(name = "created_on", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    /* cascade = CascadeType.REMOVE - if we want to erase the related gift*/
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    public GiftEntity getGiftEntity() {
        return giftEntity;
    }

    public void setGiftEntity(GiftEntity giftEntity) {
        this.giftEntity = giftEntity;
    }
}

