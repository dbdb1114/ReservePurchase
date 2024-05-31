package com.reservation.stockservice.redis;

import com.reservation.stockservice.entity.StockRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRedisRepository extends CrudRepository<StockRedis,Long> {
    StockRedis findByProductId(Long productId);
}
