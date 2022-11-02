package com.example.euphoriagifts2.service;

import com.example.euphoriagifts2.model.entity.OrderEntity;

import java.security.Principal;
import java.util.List;

public interface OrderService {
    Integer countOrders();

    OrderEntity saveOrder(OrderEntity order);

    OrderEntity orderGift(Principal principal);


    List<OrderEntity> findAllOrders();
}
