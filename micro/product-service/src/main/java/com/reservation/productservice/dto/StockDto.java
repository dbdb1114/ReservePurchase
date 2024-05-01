package com.reservation.productservice.dto;

import com.reservation.productservice.entity.Product;
import lombok.Getter;

@Getter
public class StockDto {
    private Long id;
    private Product product;
    private Integer inventory;
}
