package com.example.euphoriagifts2.service.impl;

import com.example.euphoriagifts2.model.entity.CategoryEntity;
import com.example.euphoriagifts2.model.entity.enums.CategoryNameEnum;
import com.example.euphoriagifts2.repository.CategoryRepository;
import com.example.euphoriagifts2.service.CategoryService;
import com.example.euphoriagifts2.service.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if (categoryRepository.count() > 0) {
            return;
        }
        Arrays.stream(CategoryNameEnum.values())
                .forEach(categoryNameEnum -> {
                    CategoryEntity category = new CategoryEntity();
                    category.setCategoryName(categoryNameEnum);
                    category.setDescription("Description for " + categoryNameEnum.name());

                    categoryRepository.save(category);
                });
    }

    @Override
    public CategoryEntity findByCategoryNameEnum(CategoryNameEnum category) {
        return this.categoryRepository.findByCategoryName(category).orElseThrow(() -> new ObjectNotFoundException("Gift with category name" + category+ "was not found!" ));
    }


    @Override
    public CategoryEntity findCategoryById(long id) {
        return this.categoryRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Gift with category id" + id+ "was not found!" ));

    }
}
