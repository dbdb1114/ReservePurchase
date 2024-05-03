package com.reservation.orderservice.service;

import com.reservation.orderservice.dto.OrderDto;
import com.reservation.orderservice.dto.OrderItemDto;
import com.reservation.orderservice.entity.Order;
import com.reservation.orderservice.repository.OrderRepository;
import com.reservation.orderservice.vo.request.RequestOrder;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{

    OrderRepository orderRepository;
    ModelMapper modelMapper;

    @Override
    @Transactional
    public Order makeOrder(OrderDto orderDto, List<OrderItemDto> items) {
        Order newOrder = orderDto.createNewOrder();
        Order savedOrder = orderRepository.save(newOrder);
        items.stream().forEach(orderItemDto -> savedOrder.addOrderItem(orderItemDto.createOrderItem(savedOrder)));

        return savedOrder;
    }

    @Override
    public Order orderDetail(Long orderId) {
        return orderRepository.findById(orderId).get();
    }

    @Override
    public List<Order> orderList(Long memberId) {
        return orderRepository.findAllByMemberId(memberId);
    }

    @Override
    @Transactional
    public void orderStatusUpdate() {
        LocalDateTime edDate = LocalDateTime.now();
        LocalDateTime stDate = edDate.minusDays(5);

        List<Order> orders = orderRepository.findAllByOrderDateBetween(stDate, edDate);

        orders.stream().forEach(order->{
            order.statusUpdate();
        });
    }

    @Override
    @Transactional
    public Order makeWithDrawl(RequestOrder requestOrder) {
        // 취소 가능한 주문인지 확인
        Order order = orderRepository.findById(requestOrder.getId()).get();
        if(order.isWithDrawlAble()){
            order.makeWithDrawl();
        }
        return order;
    }
}
