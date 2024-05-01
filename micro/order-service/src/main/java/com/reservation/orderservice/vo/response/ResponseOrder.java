package com.reservation.orderservice.vo.response;

import com.reservation.orderservice.entity.OrderStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ResponseOrder {
    private Long id;
    private Long memberId;
    private List<ResponseOrderItem> items = new ArrayList<>();
    private OrderStatus status;
    private LocalDateTime orderDate;
}
