package com.reservation.productservice.vo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestOrderItem {
    private Long id;
    private Long productId;
    private Integer quantity;
    private Integer orderPrice;
}
