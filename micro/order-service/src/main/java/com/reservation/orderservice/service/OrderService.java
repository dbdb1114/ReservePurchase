package com.reservation.orderservice.service;

import com.reservation.orderservice.dto.OrderDto;
import com.reservation.orderservice.dto.OrderItemDto;
import com.reservation.orderservice.entity.Order;
import java.util.List;

public interface OrderService {
    Order makeOrder(OrderDto orderDto, List<OrderItemDto> items);
    Order cancelOrder(Long orderId);
    Order orderDetail(Long orderId);
    List<Order> orderList(Long memberId);

    void orderStatusUpdate();
}
