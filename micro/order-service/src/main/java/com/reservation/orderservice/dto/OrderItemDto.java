package com.reservation.orderservice.dto;

import com.reservation.orderservice.entity.Order;
import com.reservation.orderservice.entity.OrderItem;
import lombok.Builder;

@Builder
public class OrderItemDto {

    private Long id;
    private Long productId;
    private Integer quantity;
    private Integer orderPrice;

    public OrderItem createOrderItem(Order order){
        return OrderItem.builder()
                .order(order)
                .productId(this.productId)
                .quantity(this.quantity)
                .orderPrice(this.orderPrice)
                .build();
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public void calculatePrice(Integer price) {
        this.orderPrice = this.quantity * price;
    }
}
