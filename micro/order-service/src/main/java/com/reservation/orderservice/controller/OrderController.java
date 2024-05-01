package com.reservation.orderservice.controller;

import com.reservation.orderservice.dto.OrderDto;
import com.reservation.orderservice.dto.OrderItemDto;
import com.reservation.orderservice.dto.ProductDto;
import com.reservation.orderservice.entity.Order;
import com.reservation.orderservice.feign.MemberClient;
import com.reservation.orderservice.feign.ProductClient;
import com.reservation.orderservice.service.OrderService;
import com.reservation.orderservice.vo.response.ResponseStatus;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class OrderController {

    ProductClient productClient;
    MemberClient memberClient;
    OrderService orderService;

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
