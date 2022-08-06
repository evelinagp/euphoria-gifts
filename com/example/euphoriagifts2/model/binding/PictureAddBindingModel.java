package com.example.euphoriagifts2.model.binding;

//import com.example.euphoriagifts2.model.binding.validation.MultipartFileNotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PictureAddBindingModel {
    private String title;
    private MultipartFile picture;


    public PictureAddBindingModel() {
    }

    @NotBlank(message = "Picture name is required!")
    @Size(min=1,max=20,message = "Picture name must be between 1 and 20 characters!")
    public String getTitle() {
        return title;
    }

    public PictureAddBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    //  @MultipartFileNotEmpty(message = "Picture file is required!")
    public MultipartFile getPicture() {
        return picture;
    }

    public PictureAddBindingModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
