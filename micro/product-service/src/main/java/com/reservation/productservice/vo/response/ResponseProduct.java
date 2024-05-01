package com.reservation.productservice.vo.response;

import com.reservation.productservice.dto.StockDto;
import com.reservation.productservice.entity.Stock;
import lombok.Getter;

@Getter
public class ResponseProduct {

    private Long id;
    private String name;
    private Integer price;
    private StockDto stock;

}
