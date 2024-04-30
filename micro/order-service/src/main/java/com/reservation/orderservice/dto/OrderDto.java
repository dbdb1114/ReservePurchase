package com.reservation.orderservice.dto;

import com.reservation.orderservice.entity.Order;
import com.reservation.orderservice.entity.OrderItem;
import com.reservation.orderservice.entity.OrderStatus;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private Long memberId;
    private List<OrderItem> items = new ArrayList<>();
    private OrderStatus status;
    private LocalDate orderDate;


    public Order createNewOrder(){
        return Order.builder()
                .status(OrderStatus.PREPARE)
                .memberId(this.memberId)
                .build();
    }
}
