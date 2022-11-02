package com.example.euphoriagifts2.service.impl;

import com.example.euphoriagifts2.model.entity.GiftEntity;
import com.example.euphoriagifts2.model.entity.OrderEntity;
import com.example.euphoriagifts2.model.entity.UserEntity;
import com.example.euphoriagifts2.repository.OrderRepository;
import com.example.euphoriagifts2.service.OrderService;
import com.example.euphoriagifts2.service.UserService;
import com.example.euphoriagifts2.util.cart.ShoppingCart;
import com.example.euphoriagifts2.util.cart.ShoppingCartEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ShoppingCart shoppingCart;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, ShoppingCart shoppingCart, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.shoppingCart = shoppingCart;
        this.modelMapper = modelMapper;
    }

    @Override
    public Integer countOrders() {
        return this.orderRepository.findAll().size();
    }

    @Override
    @Transactional
    public OrderEntity saveOrder(OrderEntity order) {
        return this.orderRepository.saveAndFlush(order);


    }

      @Override
    public OrderEntity orderGift(Principal principal) {
        OrderEntity order = new OrderEntity();
        order.setOrderTime(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        order.setDeliveryAddress(shoppingCart.getDeliveryAddress());
        double totalAmount = 0.00;
        Set<GiftEntity> gifts = new LinkedHashSet<>();
        for (ShoppingCartEntity shoppingCartEntity : shoppingCart.getGifts()) {
            GiftEntity gift = this.modelMapper.map(shoppingCartEntity.getGift(), GiftEntity.class);
            gifts.add(gift);
            gift.setQuantity(shoppingCartEntity.getQuantity());
            double totalPerGift = Double.parseDouble(shoppingCartEntity.getTotalPrice().toString());
            totalAmount += totalPerGift;
        }
        order.setGifts(gifts);
        UserEntity user = this.userService.findByUsername(principal.getName());
        order.setCustomer(this.modelMapper.map(user, UserEntity.class));
        order.setTotalPrice(new BigDecimal(totalAmount));
        return order;
    }

    @Override
    public List<OrderEntity> findAllOrders() {
        return this.orderRepository.findAll();
    }

}
