package com.example.euphoriagifts2.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @GetMapping()
    public String index() {
        return "index";
    }
    @GetMapping("/contacts")
    public String contacts() {
        return "contacts";
    }

    @GetMapping("/about")
    public String about() {
        return "about";

    }

}
