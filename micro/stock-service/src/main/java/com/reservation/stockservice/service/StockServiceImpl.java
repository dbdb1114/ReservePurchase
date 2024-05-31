package com.reservation.stockservice.service;

import com.reservation.stockservice.aop.DistributedLock;
import com.reservation.stockservice.entity.StockRedis;
import com.reservation.stockservice.redis.StockRedisRepository;
import com.reservation.stockservice.repository.StockRepository;
import com.reservation.stockservice.vo.RequestStock;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StockServiceImpl implements StockService{

    private final StockRedisRepository stockRedisRepository;
    private final StockRepository stockRepository;

    @Override
    @DistributedLock(key="#productId")
    public StockRedis lookUpStock(Long productId){
        return stockRedisRepository.findByProductId(productId);
    }

    @Override
    @DistributedLock(key="#productId")
    public StockRedis decreaseStock(Long productId, Integer amount) {
        StockRedis stockRedis = stockRedisRepository.findByProductId(productId);

        try{
            stockRedis = stockRedis.decreaseInventory(amount);
        } catch (Exception e){

        }

        return stockRedisRepository.save(stockRedis);
    }

    @Override
    @DistributedLock(key="#productId")
    public StockRedis increaseStock(Long productId, Integer amount) {
        StockRedis stockRedis = stockRedisRepository.findByProductId(productId);
        stockRedis = stockRedis.increaseInventory(amount);
        return stockRedisRepository.save(stockRedis);
    }

    public Boolean decreaseStock(List<RequestStock> stockList) {
        return false;
    }

    public Boolean increaseStock(List<RequestStock> stockList) {
        return false;
    }

}
