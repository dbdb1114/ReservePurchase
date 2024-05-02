package com.reservation.orderservice.vo.request;

import com.reservation.orderservice.entity.Order;
import lombok.Data;
import lombok.Getter;

@Getter
public class RequestOrderItem {
    private Long id;
    private Order order;
    private Long productId;
    private Integer quantity;
    private Integer orderPrice;
}
