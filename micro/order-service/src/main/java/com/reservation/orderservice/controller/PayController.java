package com.reservation.orderservice.controller;

import com.reservation.orderservice.entity.Order;
import com.reservation.orderservice.entity.OrderStatus;
import com.reservation.orderservice.service.PayService;
import com.reservation.orderservice.vo.request.RequestOrder;
import com.reservation.orderservice.vo.response.ResponseOrder;
import com.reservation.orderservice.vo.response.ResponseStatus;
import com.reservation.orderservice.vo.response.ResponseVo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
@AllArgsConstructor
public class PayController {

    PayService payService;
    ModelMapper modelMapper;

    @PatchMapping("/process")
    public ResponseEntity<ResponseVo> payProcess(@RequestBody RequestOrder requestOrder){
        Order order = payService.payProcess(requestOrder);
        ResponseOrder responseOrder = modelMapper.map(order, ResponseOrder.class);
        if(responseOrder.getStatus() == OrderStatus.PAYPROCESS){
            ResponseVo<ResponseOrder> responseVo = ResponseStatus.SU.getResponseVo();
            responseVo.setData(responseOrder);
            return ResponseEntity.status(HttpStatus.OK).body(responseVo);
        } else {
            ResponseVo responseVo = ResponseStatus.FA.getResponseVo();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseVo);
        }
    }

    @PatchMapping("/complete")
    public ResponseEntity<ResponseVo> completePay(@RequestBody RequestOrder requestOrder){
        Order order = payService.payComplete(requestOrder);
        ResponseOrder responseOrder = modelMapper.map(order, ResponseOrder.class);
        if(responseOrder.getStatus() == OrderStatus.PREPARE){
            ResponseVo<ResponseOrder> responseVo = ResponseStatus.SU.getResponseVo();
            responseVo.setData(responseOrder);
            return ResponseEntity.status(HttpStatus.OK).body(responseVo);
        } else {
            ResponseVo responseVo = ResponseStatus.FA.getResponseVo();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseVo);
        }
    }
}
