package com.reservation.orderservice.config;

import static org.junit.jupiter.api.Assertions.*;

import com.reservation.orderservice.entity.Order;
import com.reservation.orderservice.entity.OrderItem;
import com.reservation.orderservice.entity.OrderStatus;
import com.reservation.orderservice.repository.OrderRepository;
import com.reservation.orderservice.vo.response.ResponseOrder;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ModelMapperConfigTest {

    @Autowired
    ModelMapper mapper;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void 변환테스트(){
        Order order = orderRepository.findById(49L).get();
        ResponseOrder map = mapper.map(order, ResponseOrder.class);
        System.out.println("map = " + map);
        System.out.println("order = " + order);
    }

}
