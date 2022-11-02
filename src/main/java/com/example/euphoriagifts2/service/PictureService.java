package com.example.euphoriagifts2.service;

import com.example.euphoriagifts2.model.entity.GiftEntity;
import com.example.euphoriagifts2.model.entity.PictureEntity;
import com.example.euphoriagifts2.model.service.PictureServiceModel;
import com.example.euphoriagifts2.model.view.PictureViewModel;

import java.io.IOException;
import java.util.List;

public interface PictureService {
    List<PictureViewModel> findAllPictures();

    void deletePicture(String picId);

    void saveEntity(PictureEntity picture);
}
