package com.reservation.orderservice.config;

import com.reservation.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@AllArgsConstructor
public class ScheduleConfiguration {

    private OrderService orderService;

    @Scheduled(cron = "0 0 12 * * *")
    void run(){
        orderService.orderStatusUpdate();
    }

}
