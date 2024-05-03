package com.reservation.orderservice.vo.response;

import com.reservation.orderservice.vo.HttpOrderItem;
import lombok.Data;

@Data
public class ResponseOrderItem implements HttpOrderItem {
    private Long id;
    private Long productId;
    private Integer quantity;
    private Integer orderPrice;
}
