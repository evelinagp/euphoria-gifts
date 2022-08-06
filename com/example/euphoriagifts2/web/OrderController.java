package com.example.euphoriagifts2.web;

import com.example.euphoriagifts2.service.OrderService;
import com.example.euphoriagifts2.util.cart.ShoppingCart;
import com.example.euphoriagifts2.util.cart.ShoppingCartEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final ModelMapper modelMapper;
    private final ShoppingCart shoppingCart;
    private final OrderService orderService;

    public OrderController(ModelMapper modelMapper, ShoppingCart shoppingCart, OrderService orderService) {
        this.modelMapper = modelMapper;
        this.shoppingCart = shoppingCart;
        this.orderService = orderService;
    }


    @GetMapping("/complete-order")
    public String completeOrder() {
        return "complete-order";
    }

    @PostMapping("/complete-order")
    public String completeOrderConfirm() {
        return "redirect:/addresses/details";
    }

    @GetMapping("/removeGift/{id}")
    public String remove(@PathVariable Long id, ShoppingCart shoppingCart) {
        ShoppingCartEntity giftInCart = shoppingCart.getGifts().stream().filter(g -> g.getGift().getId() == id).findAny().orElse(null);

        if (giftInCart != null) {
            shoppingCart.getGifts().remove(giftInCart);
        }
        return "redirect:/orders/complete-order";
    }
    @GetMapping("/order-done")
    public String orderDone() {
        return "order-done";
    }

    @ModelAttribute
    private ShoppingCart shoppingCart() {
        return this.shoppingCart;
    }
}
