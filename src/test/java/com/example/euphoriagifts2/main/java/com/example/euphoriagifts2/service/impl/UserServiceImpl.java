package com.example.euphoriagifts2.service.impl;

import com.example.euphoriagifts2.model.binding.UserRegisterBindingModel;
import com.example.euphoriagifts2.model.entity.RoleEntity;
import com.example.euphoriagifts2.model.entity.UserEntity;
import com.example.euphoriagifts2.model.entity.enums.RoleNameEnum;
import com.example.euphoriagifts2.model.service.UserServiceModel;
import com.example.euphoriagifts2.repository.RoleRepository;
import com.example.euphoriagifts2.repository.UserRepository;
import com.example.euphoriagifts2.service.AppUserDetailsService;
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

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService appUserDetailsService;
    private final String adminPass;
    private final RoleService roleService;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository,
                           RoleRepository roleRepository, PasswordEncoder passwordEncoder,
                           UserDetailsService appUserDetailsService,
                           @Value("${app.default.admin.password}") String adminPass,
                           RoleService roleService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.appUserDetailsService = appUserDetailsService;
        this.adminPass = adminPass;
        this.roleService = roleService;
    }


    @Override
    public boolean isUsernameExists(UserRegisterBindingModel userRegisterBindingModel) {
        return userRepository.existsByUsername(userRegisterBindingModel.getUsername());
    }

    @Override
    public boolean isEmailExists(UserRegisterBindingModel userRegisterBindingModel) {
        return userRepository.existsByEmail(userRegisterBindingModel.getEmail());

    }

//    public void initUsersAndRoles() {
//        if (userRepository.count() == 0 && roleRepository.count() == 0) {
//            RoleEntity adminRole = new RoleEntity().setName(RoleNameEnum.ADMIN);
//            RoleEntity userRole = new RoleEntity().setName(RoleNameEnum.USER);
//
//            adminRole = roleRepository.save(adminRole);
//            userRole = roleRepository.save(userRole);
//
//            initAdmin(Set.of(adminRole, userRole));
//            initUser(Set.of(userRole));
//        }
//    }

//    private void initAdmin(Set<RoleEntity> roles) {
//        UserEntity admin = new UserEntity().
//                setRoles(roles).
//                setUsername("admin").
//                setFirstName("Admin").
//                setLastName("Adminov").
//                setAge(20).
//                setEmail("admin@example.com").
//                setPassword(passwordEncoder.encode(adminPass));
//
//        userRepository.save(admin);
//    }
//
//    private void initUser(Set<RoleEntity> roles) {
//        UserEntity user = new UserEntity().
//                setRoles(roles).
//                setUsername("user").
//                setFirstName("User").
//                setLastName("Userov").
//                setAge(18).
//                setEmail("user@example.com").
//                setPassword(passwordEncoder.encode(adminPass));
//
//        userRepository.save(user);
//    }

    public void registerAndLogin(UserRegisterBindingModel userRegisterBindingModel) {

        Set<RoleEntity> userRole = this.roleRepository.findById(2L).stream().collect(Collectors.toSet());


        UserEntity newUser =
                new UserEntity().
                        setUsername(userRegisterBindingModel.getUsername()).
                        setRoles(userRole).
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

    @Override
    public UserEntity findByUsername(String name) {
        //TODO: THROW EXCEPTION
        return this.userRepository.findByUsername(name).orElse(null);
    }

    @Override
    public void saveUser(UserEntity userEntity) {
        this.userRepository.save(userEntity);
    }

    @Override
    public boolean existByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public List<UserServiceModel> findAllUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(user -> {
                    UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);
                    user.getRoles()
                            .forEach(role -> userServiceModel.getRoles().add(role));
                    return userServiceModel;
                })
                .collect(Collectors.toList());

    }

    @Override
    public boolean existById(Long id) {
        return this.userRepository.existsById(id);

    }

    @Transactional
    @Override
    public void deleteUser(Long id) throws Exception {
        UserEntity user = this.userRepository.findById(id).orElseThrow(Exception::new);
        if (user.getId() != 1) {
            this.userRepository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public void changeCurrentUserRole(Long id) throws Exception {
        UserEntity user = this.userRepository.findById(id).orElseThrow(Exception::new);
        Set<RoleEntity> userRole = this.roleRepository.findById(2L).stream().collect(Collectors.toSet());
        Set<RoleEntity> adminRole = this.roleRepository.findById(1L).stream().collect(Collectors.toSet());
        AppUserDetailsService userDetailServiceImpl = new AppUserDetailsService(this.userRepository);
        RoleEntity adminEntity = user.getRoles().stream().filter(r -> r.getName().name().equalsIgnoreCase("ADMIN")).findAny().orElse(null);
        RoleEntity userEntity = user.getRoles().stream().filter(r -> r.getName().name().equalsIgnoreCase("USER")).findAny().orElse(null);
        if (adminEntity != null && user.getId() != 1) {
            user.setRoles(userRole);
            userDetailServiceImpl.mapUser(this.modelMapper.map(user, UserEntity.class));
        } else if (userEntity != null) {
            user.setRoles(adminRole);
            userDetailServiceImpl.mapAdmin(this.modelMapper.map(user, UserEntity.class));
        }
    }
}
