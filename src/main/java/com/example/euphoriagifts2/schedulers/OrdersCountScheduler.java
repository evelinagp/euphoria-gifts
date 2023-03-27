package com.example.euphoriagifts2.schedulers;

import com.example.euphoriagifts2.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrdersCountScheduler {

    Logger logger = LoggerFactory.getLogger(OrdersCountScheduler.class);
    private final OrderService orderService;

    public OrdersCountScheduler(OrderService orderService) {
        this.orderService = orderService;
    }

    //The idea is every month at 7 am on 1 day of the month to show that statistic,
    //but only to show that is working it's set to appear at every 30 seconds
//    @Scheduled(cron= "* 00 07 1 * *")

    @Scheduled(cron = "${schedulers.cron}")
    public void ordersCountSchedule(){

        Integer ordersCount = orderService.countOrders();

        logger.info("The orders count for last month is: {}" , ordersCount);

    }
}
