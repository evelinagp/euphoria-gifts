package com.example.euphoriagifts2.repository;

import com.example.euphoriagifts2.model.entity.RoleEntity;
import com.example.euphoriagifts2.model.entity.enums.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

}
