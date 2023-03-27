package com.example.euphoriagifts2.service;

import com.example.euphoriagifts2.model.entity.GiftEntity;
import com.example.euphoriagifts2.model.entity.OrderEntity;

import java.security.Principal;
import java.util.List;
import java.util.Set;

public interface OrderService {
    Integer countOrders();

    OrderEntity saveOrder(OrderEntity order);

    OrderEntity orderGift(Principal principal);


    List<OrderEntity> findAllOrders();

}
