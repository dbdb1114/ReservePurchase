package com.reservation.productservice.service;

import com.reservation.productservice.entity.Stock;
import com.reservation.productservice.vo.request.RequestOrderItem;
import java.util.List;

public interface StockService {

    Integer decreaseStock(List<RequestOrderItem> orderItemList);
    Integer increaseStock(List<RequestOrderItem> orderItemList);

}
