package com.reservation.orderservice.vo.request;

import com.reservation.orderservice.entity.OrderStatus;
import com.reservation.orderservice.vo.HttpOrder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestOrder implements HttpOrder {
    private Long id;
    private Long memberId;
    private List<RequestOrderItem> items = new ArrayList<>();
    private OrderStatus status;
    private LocalDateTime orderDate;
}
