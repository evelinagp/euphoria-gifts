package com.example.euphoriagifts2.service;

import com.example.euphoriagifts2.model.binding.UserRegisterBindingModel;
import com.example.euphoriagifts2.model.entity.UserEntity;
import com.example.euphoriagifts2.model.service.UserServiceModel;

import java.util.List;

public interface UserService {

    boolean isUsernameExists(UserRegisterBindingModel userRegisterBindingModel);

    boolean isEmailExists(UserRegisterBindingModel userRegisterBindingModel);

    void registerAndLogin(UserRegisterBindingModel userRegisterBindingModel);

    UserEntity findByUsername(String name);

    List<UserServiceModel> findAllUsers();

    boolean existById(Long id);

    void deleteUser(Long id) throws Exception;

    void changeCurrentUserRole(Long id) throws Exception;
}
