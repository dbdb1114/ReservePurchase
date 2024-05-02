package com.reservation.productservice.dto;

import lombok.Getter;

@Getter
public class StockDto {
    private Long id;
    private ProductDto product;
    private Integer inventory;
}
