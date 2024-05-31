package com.reservation.stockservice.config;

import com.reservation.stockservice.entity.StockJpa;
import com.reservation.stockservice.entity.StockRedis;
import org.mapstruct.factory.Mappers;

public interface StockMapper {

    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    StockJpa toStockJpa(StockRedis stock);
    StockRedis toStockRedis(StockJpa stock);
}
