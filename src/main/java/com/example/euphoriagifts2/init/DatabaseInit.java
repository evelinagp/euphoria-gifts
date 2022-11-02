package com.example.euphoriagifts2.init;

import com.example.euphoriagifts2.service.CategoryService;
import com.example.euphoriagifts2.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit implements CommandLineRunner {
    private final CategoryService categoryService;


    public DatabaseInit( CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) {
        categoryService.initCategories();
    }
}
