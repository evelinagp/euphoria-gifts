package com.example.euphoriagifts2.web;


import com.example.euphoriagifts2.model.binding.UserLoginBindingModel;
import com.example.euphoriagifts2.model.binding.UserRegisterBindingModel;
import com.example.euphoriagifts2.model.entity.GiftEntity;
import com.example.euphoriagifts2.model.service.GiftServiceModel;
import com.example.euphoriagifts2.service.GiftService;
import com.example.euphoriagifts2.service.UserService;
import com.example.euphoriagifts2.util.cart.ShoppingCart;
import com.example.euphoriagifts2.util.cart.ShoppingCartEntity;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final GiftService giftService;
    private final ModelMapper modelMapper;
    private final ShoppingCart shoppingCart;

    public UserController(UserService userService, GiftService giftService, ModelMapper modelMapper, ShoppingCart shoppingCart) {
        this.userService = userService;
        this.giftService = giftService;
        this.modelMapper = modelMapper;
        this.shoppingCart = shoppingCart;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }


    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel
            , BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        System.out.println();
        if (bindingResult.hasErrors() ||
                !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())
                || this.userService.isUsernameExists(userRegisterBindingModel)
                || this.userService.isEmailExists(userRegisterBindingModel)) {
            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }

        userService.registerAndLogin(userRegisterBindingModel);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    // NOTE: This should be post mapping!
    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String userName,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, userName);
        redirectAttributes.addFlashAttribute("bad_credentials",
                true);

        return "redirect:/users/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable Long id, Model model) {
        GiftServiceModel giftServiceModel = this.modelMapper
                .map(this.giftService.findGiftById(id), GiftServiceModel.class);
        model.addAttribute("giftServiceModel", giftServiceModel);
        return "add-to-cart";
    }

    @PostMapping("/add-to-cart/{id}")
    public String addToCartConfirm(GiftServiceModel giftServiceModel, @PathVariable Long id) {
        if (giftServiceModel.getQuantity() == null || giftServiceModel.getQuantity() <= 0) {
            return String.format("redirect:/add-to-cart/%s", id);
        }

        GiftEntity gift = this.modelMapper.map(this.giftService.findGiftById(id), GiftEntity.class);
        ShoppingCartEntity cartEntity = setShoppingCartContent(giftServiceModel, gift);

        if (shoppingCart.getGifts() != null) {
            boolean isGiftInCart = false;
            ShoppingCartEntity shoppingCartEntity = null;

            ShoppingCartEntity giftInCart = shoppingCart.getGifts().stream().filter(g -> g.getGift().getId() == id).findAny().orElse(null);
            if (giftInCart != null) {
                isGiftInCart = true;
                shoppingCartEntity = giftInCart;
            }

            if (isGiftInCart) {
                shoppingCartEntity.setQuantity(shoppingCartEntity.getQuantity() + cartEntity.getQuantity());
                return "redirect:/orders/complete-order";
            }
        }
        this.shoppingCart.getGifts().add(cartEntity);
        return "redirect:/orders/complete-order";

    }

    private ShoppingCartEntity setShoppingCartContent(GiftServiceModel giftServiceModel, GiftEntity gift) {
        ShoppingCartEntity entity = new ShoppingCartEntity();
        entity.setQuantity(giftServiceModel.getQuantity());
        entity.setPrice(gift.getPrice());
        entity.setGift(gift);
        return entity;
    }


    @ModelAttribute
    private GiftServiceModel giftServiceModel() {
        return new GiftServiceModel();
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }
}


