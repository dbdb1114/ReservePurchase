package com.reservation.productservice.vo.request;

import lombok.Data;

@Data
public class RequestWishList {
    Long id;
    String email;
    Long productId;
    Integer quantity;
}
