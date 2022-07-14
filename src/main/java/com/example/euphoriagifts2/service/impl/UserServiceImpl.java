package com.example.euphoriagifts2.service.impl;

import com.example.euphoriagifts2.model.binding.UserRegisterBindingModel;
import com.example.euphoriagifts2.model.entity.RoleEntity;
import com.example.euphoriagifts2.model.entity.UserEntity;
import com.example.euphoriagifts2.model.entity.enums.RoleNameEnum;
import com.example.euphoriagifts2.repository.RoleRepository;
import com.example.euphoriagifts2.repository.UserRepository;
import com.example.euphoriagifts2.service.RoleService;
import com.example.euphoriagifts2.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleService roleService;

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService appUserDetailsService;
    private String adminPass;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, RoleService roleService, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserDetailsService appUserDetailsService, @Value("${app.default.admin.password}") String adminPass) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.appUserDetailsService = appUserDetailsService;
        this.adminPass = adminPass;
    }


    @Override
    public boolean isUsernameExists(UserRegisterBindingModel userRegisterBindingModel) {
        return userRepository.existsByUsername(userRegisterBindingModel.getUsername());
    }

    @Override
    public boolean isEmailExists(UserRegisterBindingModel userRegisterBindingModel) {
        return userRepository.existsByEmail(userRegisterBindingModel.getEmail());

    }

    public void init() {
        if (userRepository.count() == 0 && roleRepository.count() == 0) {
            RoleEntity adminRole = new RoleEntity().setName(RoleNameEnum.ADMIN);
            RoleEntity userRole = new RoleEntity().setName(RoleNameEnum.USER);

            adminRole = roleRepository.save(adminRole);
            userRole = roleRepository.save(userRole);

            initAdmin(Set.of(adminRole, userRole));
            initUser(Set.of(userRole));
        }
    }

    private void initAdmin(Set<RoleEntity> roles) {
        UserEntity admin = new UserEntity().
                setRoles(roles).
                setUsername("admin").
                setFirstName("Admin").
                setLastName("Adminov").
                setAge(20).
                setEmail("admin@example.com").
                setPassword(passwordEncoder.encode(adminPass));

        userRepository.save(admin);
    }

    private void initUser(Set<RoleEntity> roles) {
        UserEntity user = new UserEntity().
                setRoles(roles).
                setUsername("user").
                setFirstName("User").
                setLastName("Userov").
                setAge(18).
                setEmail("user@example.com").
                setPassword(passwordEncoder.encode(adminPass));

        userRepository.save(user);
    }

    public void registerAndLogin(UserRegisterBindingModel userRegisterBindingModel) {
//        RoleEntity userRole = new RoleEntity().setName(RoleNameEnum.USER);
//        Set<RoleEntity> userRoles = new HashSet<>();
//        userRoles.add(userRole);
        UserEntity newUser =
                new UserEntity().
                        setUsername(userRegisterBindingModel.getUsername()).
                        setAge(userRegisterBindingModel.getAge()).
                        setEmail(userRegisterBindingModel.getEmail()).
                        setFirstName(userRegisterBindingModel.getFirstName()).
                        setLastName(userRegisterBindingModel.getLastName()).
                        setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));

        userRepository.save(newUser);

        UserDetails userDetails =
                appUserDetailsService.loadUserByUsername(newUser.getUsername());

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
    }
}
