package com.reservation.orderservice.controller;

import com.reservation.orderservice.dto.OrderDto;
import com.reservation.orderservice.dto.OrderItemDto;
import com.reservation.orderservice.dto.ProductDto;
import com.reservation.orderservice.entity.Order;
import com.reservation.orderservice.entity.OrderStatus;
import com.reservation.orderservice.feign.MemberClient;
import com.reservation.orderservice.feign.ProductClient;
import com.reservation.orderservice.feign.StockClient;
import com.reservation.orderservice.service.OrderService;
import com.reservation.orderservice.vo.request.RequestOrder;
import com.reservation.orderservice.vo.response.ResponseOrder;
import com.reservation.orderservice.vo.response.ResponseStatus;
import com.reservation.orderservice.vo.response.ResponseVo;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    ProductClient productClient;
    MemberClient memberClient;
    StockClient stockClient;
    OrderService orderService;
    ModelMapper modelMapper;

    @PostMapping("/list")
    public ResponseEntity<ResponseVo> orderList(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        Long memberId = memberClient.memberId(authorization);

        List<Order> orders = orderService.orderList(memberId);
        if(orders.size() > 0){
            List<ResponseOrder> orderList = orders.stream()
                    .map(order -> modelMapper.map(order, ResponseOrder.class))
                    .collect(Collectors.toList());
            ResponseVo<List<ResponseOrder>> responseVo = new ResponseVo<>(ResponseStatus.SU);
            responseVo.setData(orderList);
            return ResponseEntity.status(HttpStatus.OK).body(responseVo);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseVo<>(ResponseStatus.EC));
        }
    }

    @PostMapping("/detail/{orderId}")
    public ResponseEntity<ResponseVo> orderDetail(@PathVariable Long orderId){

        Order order = orderService.orderDetail(orderId);
        if(order != null){
            ResponseOrder responseOrder = modelMapper.map(order, ResponseOrder.class);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseVo<ResponseOrder>(ResponseStatus.SU).setData(responseOrder));
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseVo<>(ResponseStatus.FA));
        }
    }

    @PostMapping("/publish")
    public ResponseEntity makeOrder(@RequestBody ArrayList<OrderItemDto> orderItemList, HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        Long memberId = memberClient.memberId(authorization);

        OrderDto orderDto = OrderDto.builder()
                .memberId(memberId)
                .build();

        List<Long> idList = orderItemList.stream().map(OrderItemDto::getProductId).collect(Collectors.toList());
        Map<Long,ProductDto> productInfo = productClient.productInfo(idList);

        orderItemList.stream().forEach(dto -> {
            Integer price = productInfo.get(dto.getProductId()).getPrice();
            dto.calculatePrice(price);
        });

        Order order = orderService.makeOrder(orderDto, orderItemList);

        if(order != null){
            ResponseOrder responseOrder = modelMapper.map(order, ResponseOrder.class);
            stockClient.decreaseStock(responseOrder.getItems());
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseVo<ResponseOrder>(ResponseStatus.SU));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseVo<>(ResponseStatus.FA));
        }
    }

    @PatchMapping("/cancel")
    public ResponseEntity<ResponseVo> cancelOrder(@RequestBody RequestOrder requestOrder) {
        Order order = orderService.makeWithDrawl(requestOrder);
        ResponseOrder responseOrder = modelMapper.map(order, ResponseOrder.class);

        if(responseOrder.getStatus() == OrderStatus.WITHDRAWAL){
            stockClient.increaseStock(responseOrder.getItems());
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseVo<ResponseOrder>(ResponseStatus.SU).setData(responseOrder));
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseVo<>(ResponseStatus.FA));
        }

    }

    @PatchMapping("/refund")
    public ResponseEntity refundOrder(@RequestBody RequestOrder requestOrder) {
        Order order = orderService.applyRefund(requestOrder);
        ResponseOrder responseOrder = modelMapper.map(order, ResponseOrder.class);

        if(responseOrder.getStatus() == OrderStatus.APPLYREFUND) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseVo<ResponseOrder>(ResponseStatus.SU).setData(responseOrder));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseVo<>(ResponseStatus.FA));
        }
    }

}
