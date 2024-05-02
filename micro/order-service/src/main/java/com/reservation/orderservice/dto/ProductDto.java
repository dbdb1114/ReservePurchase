package com.reservation.orderservice.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long productId;
    private Integer price;
    private Integer stock;
}
