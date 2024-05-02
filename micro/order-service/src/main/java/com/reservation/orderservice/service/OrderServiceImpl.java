package com.reservation.orderservice.service;

import com.reservation.orderservice.dto.OrderDto;
import com.reservation.orderservice.dto.OrderItemDto;
import com.reservation.orderservice.entity.Order;
import com.reservation.orderservice.entity.OrderItem;
import com.reservation.orderservice.repository.OrderRepository;
import com.reservation.orderservice.vo.request.RequestOrderItem;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.antlr.v4.runtime.atn.SemanticContext.OR;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{

    OrderRepository orderRepository;

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
}
