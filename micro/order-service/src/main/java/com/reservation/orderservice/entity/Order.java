package com.reservation.orderservice.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDERS")
public class Order {
    @Id
    @Column(name = "ORDER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MEMBER_ID", nullable = false)
    private Long memberId;

    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private final List<OrderItem> items = new ArrayList<>();

    @Column(nullable = false)
    @ColumnDefault("'PREPARE'")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "ORDER_DATE")
    @CreationTimestamp
    private LocalDateTime orderDate;

    public void addOrderItem(OrderItem orderItem) {
        this.items.add(orderItem);
    }
    public void cancelOrder(){
        this.status = OrderStatus.WITHDRAWAL;
    }

    public void statusUpdate(){
        Period period = Period.between(this.orderDate.toLocalDate(), LocalDate.now());

        switch (period.getDays()) {
            case 1 : this.status = OrderStatus.SHIPPING;
                break;
            case 2, 3 : this.status = OrderStatus.DELIVERED;
                break;
            case 4 :  this.status = OrderStatus.CONFIRMPURCHASE;
                break;
        }

    }

    public void completeRefund(){
        if(this.status == OrderStatus.APPLYREFUND && !Objects.equals(this.orderDate.toLocalDate(), LocalDate.now())){
            this.status = OrderStatus.REFUNDCOMPLETE;
        }
    }

    public boolean isWithDrawlAble(){
        return this.status == OrderStatus.PREPARE || this.status == OrderStatus.BEFOREPAY;
    }
    public Order makeWithDrawl() {
        this.status = OrderStatus.WITHDRAWAL;
        return this;
    }

    public boolean isRefundAble() {
        // D+2 부터는 구매확정으로 이어지기 때문에 배송완료 상태라면 모두 환불 가능하다.
        return this.status == OrderStatus.DELIVERED;
    }

    public Order makeRefund(){
        this.status = OrderStatus.APPLYREFUND;
        return this;
    }
}
