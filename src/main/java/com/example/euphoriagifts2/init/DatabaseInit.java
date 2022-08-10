package com.example.euphoriagifts2.init;

import com.example.euphoriagifts2.service.CategoryService;
import com.example.euphoriagifts2.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit implements CommandLineRunner {
//    private final UserService userService;
    private final CategoryService categoryService;


    public DatabaseInit(/*UserService userService,*/ CategoryService categoryService) {
//        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) {
//        userService.initUsersAndRoles();
        categoryService.initCategories();
    }
}