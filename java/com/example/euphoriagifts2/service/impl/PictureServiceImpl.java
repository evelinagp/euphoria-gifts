package com.example.euphoriagifts2.service.impl;

import com.example.euphoriagifts2.model.entity.GiftEntity;
import com.example.euphoriagifts2.model.entity.PictureEntity;
import com.example.euphoriagifts2.model.service.PictureServiceModel;
import com.example.euphoriagifts2.model.view.PictureViewModel;
import com.example.euphoriagifts2.repository.PictureRepository;
import com.example.euphoriagifts2.service.CloudinaryImage;
import com.example.euphoriagifts2.service.CloudinaryService;
import com.example.euphoriagifts2.service.PictureService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;

    public PictureServiceImpl(PictureRepository pictureRepository, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.pictureRepository = pictureRepository;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }
    @Transactional
    @Override
    public List<PictureViewModel> findAllPictures() {
        return this.pictureRepository.findAll().
        stream().map(pic -> modelMapper.map(pic, PictureViewModel.class))
                .collect(Collectors.toList());
    }
    @Override
    public void deletePicture(String picId) {
        this.pictureRepository.deleteAllByPublicId(picId);
    }

//    @Transactional
//    @Override
//    public void getPictureEntity(GiftEntity savedGift, PictureServiceModel pictureServiceModel) throws IOException {
//        CloudinaryImage cloudinaryImage = this.cloudinaryService.upload(pictureServiceModel.getPicture());
//        PictureEntity picture=new PictureEntity();
//        picture.setTitle(pictureServiceModel.getTitle());
//        picture.setPublicId(cloudinaryImage.getPublicId());
//        picture.setUrl(cloudinaryImage.getUrl());
//        picture.setGift(savedGift);
//        PictureEntity savedPicture = this.pictureRepository.save(picture);
//        savedGift.getPictures().add(savedPicture);
//    }
}
