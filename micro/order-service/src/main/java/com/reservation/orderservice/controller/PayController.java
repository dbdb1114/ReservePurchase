package com.reservation.orderservice.controller;

import com.reservation.orderservice.entity.Order;
import com.reservation.orderservice.entity.OrderStatus;
import com.reservation.orderservice.feign.StockClient;
import com.reservation.orderservice.service.OrderService;
import com.reservation.orderservice.service.PayService;
import com.reservation.orderservice.vo.request.RequestOrder;
import com.reservation.orderservice.vo.response.ResponseOrder;
import com.reservation.orderservice.vo.response.ResponseStatus;
import com.reservation.orderservice.vo.response.ResponseVo;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Supplier;
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
    StockClient stockClient;
    OrderService orderService;

    @PatchMapping("/process")
    public ResponseEntity<ResponseVo> payProcess(@RequestBody RequestOrder requestOrder){
        // 20% 이탈 구현
        if(inTwentyPercent.getAsBoolean()) {
            /**
             * 재고 수정 및 해당 주문 삭제처리
             * */
            stockClient.increaseStock(requestOrder.getItems());
            orderService.deleteOrder(requestOrder);
            return twentyFailResponse.get();
        }

        Order order = payService.payProcess(requestOrder);
        ResponseOrder responseOrder = modelMapper.map(order, ResponseOrder.class);

        if(responseOrder.getStatus() == OrderStatus.PAYPROCESS){
            return successResponse.apply(responseOrder);
        } else {
            return failResponse.get();
        }
    }

    @PatchMapping("/complete")
    public ResponseEntity<ResponseVo> payComplete(@RequestBody RequestOrder requestOrder){
        // 20% 이탈 구현
        if(inTwentyPercent.getAsBoolean()) {
            /**
             * 재고 수정 및 해당 주문 삭제 처리
             * */
            stockClient.increaseStock(requestOrder.getItems());
            orderService.deleteOrder(requestOrder);
            return twentyFailResponse.get();
        }

        Order order = payService.payComplete(requestOrder);
        ResponseOrder responseOrder = modelMapper.map(order, ResponseOrder.class);

        if(responseOrder.getStatus() == OrderStatus.PREPARE){
            return successResponse.apply(responseOrder);
        } else {
            return failResponse.get();
        }
    }



    private static final BooleanSupplier inTwentyPercent = () -> Math.random()*100 > 80;
    private static final Supplier<ResponseEntity<ResponseVo>> twentyFailResponse = () ->
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseVo(ResponseStatus.FA).setData("Intention Fail"));

    private static final Function<ResponseOrder,ResponseEntity> successResponse = order -> ResponseEntity.status(HttpStatus.OK).body(new ResponseVo<ResponseOrder>(ResponseStatus.SU));
    private static final Supplier<ResponseEntity> failResponse = () -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseVo<>(ResponseStatus.FA));
}
