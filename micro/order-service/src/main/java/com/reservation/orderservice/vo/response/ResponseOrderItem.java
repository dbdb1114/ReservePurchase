package com.reservation.orderservice.vo.response;

import lombok.Data;

@Data
public class ResponseOrderItem {
    private Long id;
    private Long productId;
    private Integer quantity;
    private Integer orderPrice;
}
