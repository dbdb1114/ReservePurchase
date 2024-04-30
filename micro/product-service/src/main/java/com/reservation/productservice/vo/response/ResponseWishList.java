package com.reservation.productservice.vo.response;

import lombok.Getter;

@Getter
public class ResponseWishList{
    private Long id;
    private ResponseProduct product;
    private Integer quantity;
}
