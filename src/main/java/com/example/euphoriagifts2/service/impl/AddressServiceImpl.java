package com.example.euphoriagifts2.service.impl;

import com.example.euphoriagifts2.model.entity.AddressEntity;
import com.example.euphoriagifts2.model.service.AddressServiceModel;
import com.example.euphoriagifts2.repository.AddressRepository;
import com.example.euphoriagifts2.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AddressEntity saveAddress(AddressServiceModel addressServiceModel) {
        return this.addressRepository.save(this.modelMapper.map(addressServiceModel, AddressEntity.class));
    }
}
