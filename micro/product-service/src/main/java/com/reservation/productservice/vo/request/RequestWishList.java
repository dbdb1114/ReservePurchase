package com.reservation.productservice.vo.request;

import lombok.Data;

@Data
public class RequestWishList {
    Long id;
    Long memberId;
    Long productId;
    Integer quantity;
}
