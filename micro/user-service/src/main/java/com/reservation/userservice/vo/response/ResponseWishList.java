package com.reservation.userservice.vo.response;


import com.reservation.userservice.dto.ProductDto;
import lombok.Getter;

@Getter
public class ResponseWishList {
    private Long id;
    private Long memberId;
    private ProductDto product;
    private Integer quantity;
}
