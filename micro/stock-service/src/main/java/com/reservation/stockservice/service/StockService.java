package com.reservation.stockservice.service;

import com.reservation.stockservice.entity.StockRedis;
import com.reservation.stockservice.vo.RequestStock;
import java.util.List;

public interface StockService {

    StockRedis lookUpStock(Long productId);
    StockRedis decreaseStock(Long productId, Integer amount);
    StockRedis increaseStock(Long productId, Integer amount);
    Boolean decreaseStock(List<RequestStock> stockList);
    Boolean increaseStock(List<RequestStock> stockList);
}
