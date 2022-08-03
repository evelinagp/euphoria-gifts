package com.example.euphoriagifts2.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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

    @Column(nullable = false, columnDefinition = "TEXT")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Column(name = "created_on", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    @ManyToOne(optional = false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @ManyToOne(optional = false)
    public GiftEntity getGiftEntity() {
        return giftEntity;
    }

    public void setGiftEntity(GiftEntity giftEntity) {
        this.giftEntity = giftEntity;
    }
}

