package com.example.euphoriagifts2.service.impl;

import com.example.euphoriagifts2.model.entity.RoleEntity;
import com.example.euphoriagifts2.model.entity.enums.RoleNameEnum;
import com.example.euphoriagifts2.repository.RoleRepository;
import com.example.euphoriagifts2.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
//    private final RoleRepository roleRepository;
//
//    @Autowired
//    public RoleServiceImpl(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }
//
//    @Override
//    public Set<RoleEntity> findRoleByName(RoleNameEnum roleName) {
//        return this.roleRepository.findByRoleName(roleName).stream().collect(Collectors.toSet());
//        //.orElseThrow(Exception::new);
//
//    }
}
