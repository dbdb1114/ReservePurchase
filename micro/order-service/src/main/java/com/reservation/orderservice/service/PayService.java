package com.reservation.orderservice.service;

import com.reservation.orderservice.entity.Order;
import com.reservation.orderservice.vo.request.RequestOrder;

public interface PayService {
    Order payProcess(RequestOrder order);
    Order payComplete(RequestOrder order);
}
