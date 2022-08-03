package com.example.euphoriagifts2.web;

import com.example.euphoriagifts2.model.entity.enums.CategoryNameEnum;
import com.example.euphoriagifts2.service.GiftService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final GiftService giftService;

    public CategoryController(GiftService giftService) {
        this.giftService = giftService;
    }

//    @GetMapping("/categories/home")
//    public String homeCategory(Model model) {
//        model.addAttribute("home", giftService
//                .getAllGiftsByCategoryName(CategoryNameEnum.HOME));
////
////        model.addAttribute("drinks", productService
////                .findAllProductsByCategoryName(CategoryNameEnum.DRINK));
//
//        return "home-category";
//    }

}
