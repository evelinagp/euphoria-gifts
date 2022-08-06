package com.example.euphoriagifts2.repository;

import com.example.euphoriagifts2.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {


    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String password);


}
