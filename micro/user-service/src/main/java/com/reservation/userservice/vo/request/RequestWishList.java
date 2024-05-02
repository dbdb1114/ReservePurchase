package com.reservation.userservice.vo.request;

import lombok.Setter;

@Setter
public class RequestWishList {
    Long id;
    Long memberId;
    Long productId;
    Integer quantity;
}
