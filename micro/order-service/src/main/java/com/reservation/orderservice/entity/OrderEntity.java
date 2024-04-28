package com.reservation.orderservice.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDERS")
public class OrderEntity {
    @Id
    @Column(name = "ORDERS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "MEMBER_ID")
    private Long memberId;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY)
    private List<OrderItem> items = new ArrayList<OrderItem>();

    private String status;

    private LocalDate order_date;

    public Long getOrderId(){
        return this.id;
    }

}
