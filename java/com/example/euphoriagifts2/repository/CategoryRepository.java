package com.example.euphoriagifts2.repository;

import com.example.euphoriagifts2.model.entity.CategoryEntity;
import com.example.euphoriagifts2.model.entity.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByCategoryName(CategoryNameEnum category);
}
