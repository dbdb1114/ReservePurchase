package com.reservation.orderservice.service;

import static org.junit.jupiter.api.Assertions.*;

import com.reservation.orderservice.dto.OrderDto;
import com.reservation.orderservice.dto.OrderItemDto;
import com.reservation.orderservice.entity.Order;
import com.reservation.orderservice.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void 주문만들기(){
        List<OrderItemDto> items = new ArrayList<>();
        OrderDto orderDto = OrderDto.builder()
                .memberId(3L).build();

        items.add(OrderItemDto.builder()
                .productId(1L)
                .quantity(1)
                .orderPrice(1000)
                .build());
        items.add(OrderItemDto.builder()
                .productId(4L)
                .quantity(1)
                .orderPrice(1000)
                .build());
        items.add(OrderItemDto.builder()
                .productId(3L)
                .quantity(1)
                .orderPrice(1000)
                .build());
        items.add(OrderItemDto.builder()
                .productId(2L)
                .quantity(1)
                .orderPrice(1000)
                .build());

        orderService.makeOrder(orderDto, items);
        List<Order> all = orderRepository.findAll();
        for (Order order : all) {
            System.out.println(order);
        }

    }

}
