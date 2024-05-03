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
    void updateOrderStatus(){
        log.info("===========================배송현황을 업데이트 합니다.===========================");
        orderService.orderStatusUpdate();
    }

    @Scheduled(cron = "0 49 14 * * *")
    void updateRefundStatus(){
        log.info("===========================환불 정책을 실행합니다.===========================");
        orderService.updateRefundStatus();
    }

}
