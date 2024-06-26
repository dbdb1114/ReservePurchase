package com.reservation.orderservice.vo.response;

import com.reservation.orderservice.entity.OrderStatus;
import com.reservation.orderservice.vo.HttpOrder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ResponseOrder implements HttpOrder {
    private Long id;
    private Long memberId;
    private List<ResponseOrderItem> items = new ArrayList<>();
    private OrderStatus status;
    private LocalDateTime orderDate;
}
