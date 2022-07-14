package com.example.euphoriagifts2.init;

import com.example.euphoriagifts2.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit implements CommandLineRunner {
    private final UserService userService;

    public DatabaseInit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        userService.init();
    }
}
