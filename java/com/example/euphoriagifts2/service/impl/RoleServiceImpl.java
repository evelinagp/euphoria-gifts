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

//    @Override
//    public RoleEntity findRoleByName(RoleNameEnum user) {
//        return this.roleRepository.findByRoleName(user)
//                .orElseThrow(
//                        () -> new IllegalStateException("There is no user role! Please init the roles."));
//    }

//    @Override
//    public RoleEntity findRole(RoleNameEnum roleName) {
//            return this.roleRepository.findByRoleName(roleName);
//    }

//    @Override
//    public Set<RoleEntity> findRoleByName(RoleNameEnum roleName) {
//        return this.roleRepository.findByRoleName(roleName);
//    }
}
////        public Role findRoleByName(RoleEnum name) throws Exception {
////            return this.roleRepository.findRoleByName(name).orElseThrow(Exception::new);
////        }
//        //.orElseThrow(Exception::new);
//
//    }
