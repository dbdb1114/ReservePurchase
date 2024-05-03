package com.reservation.orderservice.entity;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderTest {

    @Test
    void 날짜차이구하기(){
        Order order = Order.builder()
                .orderDate(LocalDateTime.now().minusDays(3))
                .build();

        order.statusUpdate();
    }
}
