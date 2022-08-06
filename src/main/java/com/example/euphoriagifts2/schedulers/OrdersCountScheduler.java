package com.example.euphoriagifts2.schedulers;

import com.example.euphoriagifts2.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

public class OrdersCountScheduler {


    Logger logger = LoggerFactory.getLogger(OrdersCountScheduler.class);
    private final OrderService orderService;

    public OrdersCountScheduler(OrderService orderService) {
        this.orderService = orderService;
    }


    @Scheduled(cron = "${schedulers.cron}")
    public void tripsCountSchedule(){

        Integer ordersCount = orderService.countOrders();

        logger.info("The orders count for last month is: {}" , ordersCount);

    }
}
