package com.example.euphoriagifts2.web;


import com.example.euphoriagifts2.model.service.GiftServiceModel;
import com.example.euphoriagifts2.service.GiftService;
import com.example.euphoriagifts2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/pages/admins")
public class AdminController {

    private final GiftService giftService;
    private final UserService userService;
    // private final OrderService orderService;

    public AdminController(GiftService giftService, UserService userService/*, OrderService orderService*/) {
        this.giftService = giftService;
        this.userService = userService;
        //   this.orderService = orderService;
    }

    @GetMapping("/edit-gift/{id}")
    public String edit(@PathVariable Long id, Model model) {
        //  GiftServiceModel gift = this.giftService.findById(id);
//           model.addAttribute("GiftServiceModel", gift);

        return "/edit-gift";
    }

    @PostMapping("/edit-gift/{id}")
    public String editPost(@Valid GiftServiceModel giftServiceModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes,
                           @RequestParam("file") MultipartFile file, @PathVariable Long id) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("GiftServiceModel", giftServiceModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.giftServiceModel", bindingResult);

            return "/edit-gift";
        }

        return "redirect:/gifts";
    }
}
