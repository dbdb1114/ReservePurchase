package com.reservation.stockservice.config;

import com.reservation.stockservice.entity.StockJpa;
import com.reservation.stockservice.entity.StockRedis;
import org.springframework.stereotype.Component;

@Component
public class StockMapperImpl implements StockMapper{
    @Override
    public StockJpa toStockJpa(StockRedis stock) {
        return null;
    }

    @Override
    public StockRedis toStockRedis(StockJpa stock) {
        return StockRedis.builder()
                .productId(stock.getProductId())
                .inventory(stock.getInventory())
                .build();
    }
}
