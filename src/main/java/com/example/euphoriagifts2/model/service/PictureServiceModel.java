package com.example.euphoriagifts2.model.service;

import com.example.euphoriagifts2.model.binding.PictureAddBindingModel;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PictureServiceModel {
    private String title;
    private MultipartFile picture;


    public PictureServiceModel() {
    }


    public String getTitle() {
        return title;
    }

    public PictureServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    //  @MultipartFileNotEmpty(message = "Picture file is required!")
    public MultipartFile getPicture() {
        return picture;
    }

    public PictureServiceModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
