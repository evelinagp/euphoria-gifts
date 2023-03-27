package com.example.euphoriagifts2.service;

import com.example.euphoriagifts2.model.entity.GiftEntity;
import com.example.euphoriagifts2.model.entity.enums.CategoryNameEnum;
import com.example.euphoriagifts2.model.service.GiftServiceModel;
import com.example.euphoriagifts2.model.view.GiftViewModel;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface GiftService {
    void deleteGiftById(Long id);

    void addNewGift(GiftServiceModel giftServiceModel, Principal principal) throws IOException;

    boolean isGiftExists(String name);

    List<GiftViewModel> findAllGifts();

    GiftEntity findGiftById(Long id);

    List<GiftViewModel> getAllGiftsByCategoryName(CategoryNameEnum homeCategory);

    void updateGift(GiftServiceModel giftServiceModel) throws IOException;

}
