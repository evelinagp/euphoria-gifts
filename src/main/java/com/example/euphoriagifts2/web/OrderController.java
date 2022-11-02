package com.example.euphoriagifts2.web;

import com.example.euphoriagifts2.model.entity.AddressEntity;
import com.example.euphoriagifts2.model.entity.OrderEntity;
import com.example.euphoriagifts2.model.service.AddressServiceModel;
import com.example.euphoriagifts2.service.OrderService;
import com.example.euphoriagifts2.util.cart.ShoppingCart;
import com.example.euphoriagifts2.util.cart.ShoppingCartEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final ShoppingCart shoppingCart;
    private final OrderService orderService;

    public OrderController( ShoppingCart shoppingCart, OrderService orderService) {

        this.shoppingCart = shoppingCart;
        this.orderService = orderService;
    }

    @GetMapping("/complete-order")
    public String completeOrder() {
        return "complete-order";
    }

    @PostMapping("/complete-order")
    public String completeOrderConfirm() {
        return "redirect:/addresses/address-details";
    }

    @GetMapping("/removeGift/{id}")
    public String removeGiftFromCart(@PathVariable Long id, ShoppingCart shoppingCart) {
        ShoppingCartEntity giftInCart = shoppingCart.getGifts().stream().filter(g -> g.getGift().getId() == id).findAny().orElse(null);

        if (giftInCart != null) {
            shoppingCart.getGifts().remove(giftInCart);
        }
        return "complete-order";
    }

    @GetMapping("/order-done")
    public String orderDetails(Model model, Principal principal) throws Exception {
        if (!shoppingCart.getPaid() || shoppingCart.getDeliveryAddress() == null || shoppingCart.getGifts().size() == 0) {
            return "redirect:/gifts/all-gifts";
        }
        OrderEntity order = this.orderService.orderGift(principal);
        OrderEntity orderEntity = this.orderService.saveOrder(order);
        model.addAttribute("orderEntity", orderEntity);

        this.shoppingCart.destroy();
        return "order-done";
    }

    @ModelAttribute
    private ShoppingCart shoppingCart() {
        return this.shoppingCart;
    }
}
