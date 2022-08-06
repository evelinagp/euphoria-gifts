package com.example.euphoriagifts2.web;

import com.example.euphoriagifts2.service.AddressService;
import com.example.euphoriagifts2.service.CartService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {
    private final CartService cartService;
    private final AddressService addressService;

    public CartController(CartService cartService, AddressService addressService) {
        this.cartService = cartService;
        this.addressService = addressService;
    }

    @GetMapping("/cart")
    public String showCart(Model model, @AuthenticationPrincipal UserDetails userDetails) {

        return "complete-order";
    }
}
