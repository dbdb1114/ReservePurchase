package com.reservation.orderservice.service;

import com.reservation.orderservice.dto.OrderDto;
import com.reservation.orderservice.dto.OrderItemDto;
import com.reservation.orderservice.entity.Order;
import com.reservation.orderservice.entity.OrderStatus;
import com.reservation.orderservice.repository.OrderRepository;
import com.reservation.orderservice.vo.request.RequestOrder;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class OrderServiceImplTest {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ModelMapper modelMapper;

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

    @Test
    void 특정일전주문조회(){

        LocalDateTime edDate = LocalDateTime.now();
        LocalDateTime stDate = edDate.minusDays(3);

        OrderStatus[] orderStatuses = {OrderStatus.PREPARE, OrderStatus.SHIPPING, OrderStatus.DELIVERED};
        List<Order> between = orderRepository.findAllByOrderDateBetweenAndStatusIsIn(LocalDateTime.now().minusDays(5),LocalDateTime.now(),orderStatuses);

        for (Order order : between) {
            order.statusUpdate();
            System.out.println("order = " + order);
        }
    }


//    @BeforeEach
    void 취소주문생성(){
        System.out.println("취소주문생성");
        System.out.println("취소주문생성");
        System.out.println("취소주문생성");
        System.out.println("취소주문생성");
        List<OrderItemDto> items1 = new ArrayList<>();
        OrderDto orderDto1 = OrderDto.builder()
                .memberId(3L).build();

        items1.add(OrderItemDto.builder()
                .productId(1L)
                .quantity(1)
                .orderPrice(1000)
                .build());
        items1.add(OrderItemDto.builder()
                .productId(4L)
                .quantity(1)
                .orderPrice(1000)
                .build());
        items1.add(OrderItemDto.builder()
                .productId(3L)
                .quantity(1)
                .orderPrice(1000)
                .build());
        items1.add(OrderItemDto.builder()
                .productId(2L)
                .quantity(1)
                .orderPrice(1000)
                .build());
        Order order1 = orderService.makeOrder(orderDto1, items1);


        List<OrderItemDto> items2 = new ArrayList<>();
        OrderDto orderDto2 = OrderDto.builder()
                .memberId(3L).build();

        items2.add(OrderItemDto.builder()
                .productId(1L)
                .quantity(1)
                .orderPrice(1000)
                .build());
        items2.add(OrderItemDto.builder()
                .productId(4L)
                .quantity(1)
                .orderPrice(1000)
                .build());
        items2.add(OrderItemDto.builder()
                .productId(3L)
                .quantity(1)
                .orderPrice(1000)
                .build());
        items2.add(OrderItemDto.builder()
                .productId(2L)
                .quantity(1)
                .orderPrice(1000)
                .build());
        Order order2 = orderService.makeOrder(orderDto2, items2);
    }
    @Test
    @DisplayName("주문 취소하기 !")
    void 주문_취소하기() {
        //given
        OrderStatus[] orderStatuses = {OrderStatus.PREPARE, OrderStatus.SHIPPING, OrderStatus.DELIVERED};
        List<Order> between = orderRepository.findAllByOrderDateBetweenAndStatusIsIn(LocalDateTime.now().minusDays(1),LocalDateTime.now(),orderStatuses);

        Order order1 = between.get(0);
        Order order2 = between.get(1);

        //when
        RequestOrder requestOrder1 = modelMapper.map(order1, RequestOrder.class);
        RequestOrder requestOrder2 = modelMapper.map(order2, RequestOrder.class);

        Order saved1 = orderService.makeWithDrawl(requestOrder1);
        Order saved2 = orderService.makeWithDrawl(requestOrder2);

        //then
        RequestOrder reqSaved1 = modelMapper.map(saved1, RequestOrder.class);
        RequestOrder reqSaved2 = modelMapper.map(saved2, RequestOrder.class);

        Assertions.assertThat(reqSaved1.getStatus()).isEqualTo(OrderStatus.WITHDRAWAL);
        Assertions.assertThat(reqSaved2.getStatus()).isEqualTo(OrderStatus.WITHDRAWAL);
    }
}
