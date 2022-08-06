package com.example.euphoriagifts2.service.impl;

import com.example.euphoriagifts2.model.entity.RoleEntity;
import com.example.euphoriagifts2.model.entity.enums.RoleNameEnum;
import com.example.euphoriagifts2.repository.RoleRepository;
import com.example.euphoriagifts2.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    }
