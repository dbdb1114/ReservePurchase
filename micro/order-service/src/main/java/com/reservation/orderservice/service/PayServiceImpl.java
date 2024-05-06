package com.reservation.orderservice.service;

import com.reservation.orderservice.entity.Order;
import com.reservation.orderservice.repository.OrderRepository;
import com.reservation.orderservice.vo.request.RequestOrder;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PayServiceImpl implements PayService{

    OrderRepository orderRepository;
    ModelMapper modelMapper;

    @Override
    @Transactional
    public Order payProcess(RequestOrder requestOrder) {
        Order order = orderRepository.findById(requestOrder.getId()).get();
        order = order.inPayProcess();
        return order;
    }

    @Override
    @Transactional
    public Order payComplete(RequestOrder requestOrder) {
        Order order = orderRepository.findById(requestOrder.getId()).get();
        order = order.payComplete();
        return order;
    }
}
