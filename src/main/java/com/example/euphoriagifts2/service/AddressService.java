package com.example.euphoriagifts2.service;

import com.example.euphoriagifts2.model.entity.AddressEntity;
import com.example.euphoriagifts2.model.service.AddressServiceModel;

public interface AddressService {
    AddressEntity saveAddress(AddressServiceModel addressServiceModel);
}
