package com.example.euphoriagifts2.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pictures")
public class PictureEntity extends BaseEntity {

    private String title;

    private String url;

    private String publicId;
//    private GiftEntity giftEntity;

    public PictureEntity() {
    }

    @Column(nullable = false)
    public String getUrl() {
        return url;
    }

    public PictureEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public PictureEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    @Column(name = "public_id", nullable = false)
    public String getPublicId() {
        return publicId;
    }

    public PictureEntity setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

//    @ManyToOne(optional = false)
//    public GiftEntity getGift() {
//        return giftEntity;
//    }
//
//    public PictureEntity setGift(GiftEntity giftEntity) {
//        this.giftEntity = giftEntity;
//        return this;
//    }
}
