package com.reservation.orderservice.vo.request;

import com.reservation.orderservice.entity.Order;
import com.reservation.orderservice.vo.HttpOrderItem;
import lombok.Getter;

@Getter
public class RequestOrderItem implements HttpOrderItem {
    private Long id;
//    private Order order;
    private Long productId;
    private Integer quantity;
    private Integer orderPrice;
}
