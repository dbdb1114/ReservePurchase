package com.reservation.productservice.service;

import com.reservation.productservice.entity.Stock;
import com.reservation.productservice.repository.StockRepository;
import com.reservation.productservice.vo.request.RequestOrderItem;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StockServiceImpl implements StockService{

    StockRepository stockRepository;

    @Override
    @Transactional
    public Integer decreaseStock(List<RequestOrderItem> orderItemList) {
        final int[] count = {0};

        orderItemList.stream().forEach(item -> {
            Stock savedStock = stockRepository.findByProductId(item.getProductId());
            savedStock.decreaseInventory(item.getQuantity());
            count[0]++;
        });

        return count[0];
    }

    @Override
    @Transactional
    public Integer increaseStock(List<RequestOrderItem> orderItemList) {
        final int[] count = {0};

        orderItemList.stream().forEach(item -> {
            Stock savedStock = stockRepository.findByProductId(item.getProductId());
            savedStock.increaseInventory(item.getQuantity());
            count[0]++;
        });

        return count[0];
    }
}
