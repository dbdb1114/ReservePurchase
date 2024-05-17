package com.reservation.orderservice.controller;

import static org.assertj.core.api.Assertions.*;

import com.reservation.orderservice.dto.OrderDto;
import com.reservation.orderservice.dto.OrderItemDto;
import com.reservation.orderservice.entity.Order;
import com.reservation.orderservice.entity.OrderStatus;
import com.reservation.orderservice.repository.OrderRepository;
import com.reservation.orderservice.service.OrderService;
import com.reservation.orderservice.vo.request.RequestOrder;
import com.reservation.orderservice.vo.response.ResponseOrder;
import com.reservation.orderservice.vo.response.ResponseVo;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
@Transactional
class PayControllerTest {

    @Autowired
    PayController payController;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ModelMapper modelMapper;

    @Test
    void 판매프로세스_등록(){
        //given
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
        Order order1 = orderService.makeOrder(orderDto, items);
        RequestOrder map = modelMapper.map(order1, RequestOrder.class);

        Order order = orderRepository.findById(map.getId()).get();

        //when
        RequestOrder requestOrder = modelMapper.map(order, RequestOrder.class);
        ResponseEntity<ResponseVo> responseVoResponseEntity = payController.payProcess(requestOrder);

        //then
        ResponseOrder map1 = modelMapper.map(responseVoResponseEntity.getBody().getData(), ResponseOrder.class);
        assertThat(map1.getStatus()).isEqualTo(OrderStatus.PAYPROCESS);
    }

    @Test
    void 결제완료(){
        //given
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
        Order order1 = orderService.makeOrder(orderDto, items);
        RequestOrder map = modelMapper.map(order1, RequestOrder.class);

        Order order = orderRepository.findById(map.getId()).get();

        //when
        RequestOrder requestOrder = modelMapper.map(order, RequestOrder.class);
        ResponseEntity<ResponseVo> responseVoResponseEntity = payController.completePay(requestOrder);

        //then
        ResponseOrder map1 = modelMapper.map(responseVoResponseEntity.getBody().getData(), ResponseOrder.class);
        assertThat(map1.getStatus()).isEqualTo(OrderStatus.PREPARE);
    }
}
