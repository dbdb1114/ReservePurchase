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
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class OrderController {

    ProductClient productClient;
    MemberClient memberClient;
    OrderService orderService;
    /**
     * 요구사항
     * 1. MemberId로 Order객체를 생성한다.
     * 2. 요청받은 주문의 상품에 대한 정보를 가져온다. ( 가격 및 차후 재고처리 )
     * 3. 상품에 대한 정보를 기반으로 OrderDto를 만들어준다.
     * 4. Order객체와 orderItems를 기반으로 주문을 생성 후 저장한다.
     * */
    @GetMapping("/order")
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
