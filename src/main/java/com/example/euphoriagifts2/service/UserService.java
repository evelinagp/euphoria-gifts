package com.example.euphoriagifts2.service;

import com.example.euphoriagifts2.model.binding.UserRegisterBindingModel;

public interface UserService {

    boolean isUsernameExists(UserRegisterBindingModel userRegisterBindingModel);

    boolean isEmailExists(UserRegisterBindingModel userRegisterBindingModel);

    void init();

    void registerAndLogin(UserRegisterBindingModel userRegisterBindingModel);
}
