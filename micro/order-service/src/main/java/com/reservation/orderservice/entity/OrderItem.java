package com.reservation.orderservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id
    @Column(name = "ORDER_ITEM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "orders_id")
    private Long orderId;

    @Column(name = "product_id")
    private Long productId;

    private Integer count;

    @Column(name = "ORDER_PRICE")
    private Integer orderPrice;

}
