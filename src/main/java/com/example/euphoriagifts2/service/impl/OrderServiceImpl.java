package com.example.euphoriagifts2.service.impl;

import com.example.euphoriagifts2.repository.OrderRepository;
import com.example.euphoriagifts2.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Integer countOrders() {
        return this.orderRepository.findAll().size();
    }
}
