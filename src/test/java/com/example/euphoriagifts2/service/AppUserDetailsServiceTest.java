package com.example.euphoriagifts2.service;

import com.example.euphoriagifts2.model.entity.RoleEntity;
import com.example.euphoriagifts2.model.entity.UserEntity;
import com.example.euphoriagifts2.model.entity.enums.RoleNameEnum;
import com.example.euphoriagifts2.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AppUserDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepo;
    private AppUserDetailsService toTest;

    @BeforeEach
    void setUp() {
        toTest = new AppUserDetailsService(mockUserRepo);
    }

    @Test
    void testLoadUserByUsername_UserExists() {
        //arrange

        UserEntity testUserEntity = new UserEntity()
                .setEmail("admin@example.com")
                .setPassword("admin")
                .setUsername("admin")
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setAge(20)
                .setRoles(Set.of(new RoleEntity().setName(RoleNameEnum.ADMIN),
                        new RoleEntity().setName(RoleNameEnum.USER)));

        when(mockUserRepo.findByUsername(testUserEntity.getUsername()))
                .thenReturn(Optional.of(testUserEntity));

        // act
        UserDetails userDetails = (UserDetails) toTest
                .loadUserByUsername(testUserEntity.getUsername());

        // assert
        Assertions.assertEquals(testUserEntity.getUsername(), userDetails.getUsername());
        Assertions.assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(userDetails.getAuthorities().toArray()[1].toString(), "ROLE_USER");
        Assertions.assertEquals(userDetails.getAuthorities().toArray()[0].toString(), "ROLE_ADMIN");

        var authorities = userDetails.getAuthorities();

        var authoritiesIter = authorities.iterator();
        Assertions.assertEquals("ROLE_" + RoleNameEnum.ADMIN.name(),
                authoritiesIter.next().getAuthority());
        Assertions.assertEquals("ROLE_" + RoleNameEnum.USER.name(),
                authoritiesIter.next().getAuthority());
    }


    @Test
    public void testLoadUserByUsername_UserDoesNotExists()  {
//        arrage
//        No need to arrange anything, mocks return empty optionals

//        act and assert
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername("Not_Existed_Name")
        );
    }

    @Test
    public void mapUser() {
        AppUserDetailsService newUser = new AppUserDetailsService(mockUserRepo);
        UserEntity testUserEntity = new UserEntity();
        testUserEntity.setEmail("user@example.com")
                .setPassword("admin")
                .setUsername("user")
                .setFirstName("User")
                .setLastName("Userov")
                .setAge(18)
                .setRoles(Set.of(new RoleEntity().setName(RoleNameEnum.USER)));
        UserDetails userDetails = newUser.mapUser(testUserEntity);
        assertEquals(userDetails.getAuthorities().toArray()[0].toString(), "USER");
    }

    @Test
    public void mapAdmin(){
        AppUserDetailsService newUser = new AppUserDetailsService(mockUserRepo);
        UserEntity testUserEntity = new UserEntity()
                .setEmail("admin@example.com")
                .setPassword("admin")
                .setUsername("admin")
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setAge(20)
                .setRoles(Set.of(new RoleEntity().setName(RoleNameEnum.ADMIN)));

        UserDetails userDetails = newUser.mapAdmin(testUserEntity);
        assertEquals(userDetails.getAuthorities().toArray()[0].toString(), "ADMIN");
    }
}