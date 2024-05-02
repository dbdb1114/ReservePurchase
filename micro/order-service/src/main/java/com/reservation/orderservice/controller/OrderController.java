package com.reservation.orderservice.controller;

import com.reservation.orderservice.dto.OrderDto;
import com.reservation.orderservice.dto.OrderItemDto;
import com.reservation.orderservice.dto.ProductDto;
import com.reservation.orderservice.entity.Order;
import com.reservation.orderservice.feign.MemberClient;
import com.reservation.orderservice.feign.ProductClient;
import com.reservation.orderservice.service.OrderService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderController {

    ProductClient productClient;
    MemberClient memberClient;
    OrderService orderService;
    ModelMapper modelMapper;

    @PostMapping("/order/list")
    public ResponseEntity<ResponseVo> orderList(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        Long memberId = memberClient.memberId(authorization);

        List<Order> orders = orderService.orderList(memberId);
        if(orders.size() > 0){
            List<ResponseOrder> orderList = orders.stream()
                    .map(order -> modelMapper.map(order, ResponseOrder.class))
                    .collect(Collectors.toList());
            ResponseVo<List<ResponseOrder>> responseVo = ResponseStatus.SU.responseVo;
            responseVo.setData(orderList);
            return ResponseEntity.status(HttpStatus.OK).body(responseVo);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ResponseStatus.EC.responseVo);
        }
    }

    @PostMapping("/order/detail/{orderId}")
    public ResponseEntity<ResponseVo> orderDetail(@PathVariable Long orderId){

        Order order = orderService.orderDetail(orderId);
        if(order != null){
            ResponseVo<ResponseOrder> responseVo = ResponseStatus.SU.responseVo;
            ResponseOrder responseOrder = modelMapper.map(order, ResponseOrder.class);
            responseVo.setData(responseOrder);
            return ResponseEntity.status(HttpStatus.OK).body(responseVo);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ResponseStatus.FA.responseVo);
        }
    }

    @PostMapping("/order")
    public ResponseEntity makeOrder(@RequestBody ArrayList<OrderItemDto> orderItemList, HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        Long memberId = memberClient.memberId(authorization);

        OrderDto orderDto = OrderDto.builder()
                .memberId(memberId)
                .build();

        List<Long> idList = orderItemList.stream().map(item -> item.getProductId()).collect(Collectors.toList());
        Map<Long,ProductDto> productInfo = productClient.productInfo(idList);

        orderItemList.stream().forEach(dto -> {
            Integer price = productInfo.get(dto.getProductId()).getPrice();
            dto.calculatePrice(price);
        });

        Order order = orderService.makeOrder(orderDto, orderItemList);

        if(order != null){
            return ResponseEntity.status(HttpStatus.OK).body(ResponseStatus.SU.responseVo);
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ResponseStatus.FA.responseVo);
        }
    }

}
