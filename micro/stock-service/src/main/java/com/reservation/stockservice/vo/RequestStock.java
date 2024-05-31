package com.reservation.stockservice.vo;

import com.reservation.stockservice.entity.Stock;
import lombok.Data;

@Data
public class RequestStock implements Stock {
    Long productId;
    Integer amount;
}
