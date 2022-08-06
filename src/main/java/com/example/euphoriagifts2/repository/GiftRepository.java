package com.example.euphoriagifts2.repository;

import com.example.euphoriagifts2.model.entity.CategoryEntity;
import com.example.euphoriagifts2.model.entity.GiftEntity;
import com.example.euphoriagifts2.model.entity.PictureEntity;
import com.example.euphoriagifts2.model.entity.enums.CategoryNameEnum;
import com.example.euphoriagifts2.model.service.GiftServiceModel;
import com.example.euphoriagifts2.model.view.GiftViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface GiftRepository extends JpaRepository<GiftEntity, Long> {
    boolean existsByName(String name);

    List<GiftEntity> findAllByCategory_CategoryName(CategoryNameEnum homeCategory);

}
