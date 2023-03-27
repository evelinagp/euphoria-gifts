package com.example.euphoriagifts2.service;


import com.example.euphoriagifts2.model.entity.RoleEntity;
import com.example.euphoriagifts2.model.entity.UserEntity;
import com.example.euphoriagifts2.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

// NOTE: This is not annotated as @Service, because we will return it as a bean.
    public class AppUserDetailsService implements UserDetailsService {

        private final UserRepository userRepository;

        public AppUserDetailsService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public UserDetails loadUserByUsername(String username)
                throws UsernameNotFoundException {
            return userRepository.
                    findByUsername(username).
                    map(this::map).
                    orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found!"));
        }

        private UserDetails map(UserEntity userEntity) {
            return
                    User.builder().
                            username(userEntity.getUsername()).
                            password(userEntity.getPassword()).
                            authorities(userEntity.
                                    getRoles().
                                    stream().
                                    map(this::map).
                                    collect(Collectors.toSet())).
                            build();
        }

        private GrantedAuthority map(RoleEntity roleEntity) {
            return new SimpleGrantedAuthority("ROLE_" +
                    roleEntity.getName().name());
        }

    public UserDetails mapUser(UserEntity userEntity) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));

        return new User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                grantedAuthorities
        );
    }

    public UserDetails mapAdmin(UserEntity userEntity) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));

        return new User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                grantedAuthorities
        );
    }
}
