package com.example.euphoriagifts2.service;

import com.example.euphoriagifts2.model.entity.CategoryEntity;
import com.example.euphoriagifts2.model.entity.enums.CategoryNameEnum;

public interface CategoryService {
    void initCategories();

    CategoryEntity findByCategoryNameEnum(CategoryNameEnum category);

    CategoryEntity findCategoryById(long id);


}
