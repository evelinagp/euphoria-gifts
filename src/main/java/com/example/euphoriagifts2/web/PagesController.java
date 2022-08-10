package com.example.euphoriagifts2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages")
    public class PagesController {


        @GetMapping("/admins")
        public String admins() {
            return "manage-users";
        }
    }