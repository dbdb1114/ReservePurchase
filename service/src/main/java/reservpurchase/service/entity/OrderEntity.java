package reservpurchase.service.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private List<OrderItemEntity> items = new ArrayList<OrderItemEntity>();

    private String status;

    private LocalDate order_date;

//    public void addItems(OrderItemEntity orderItem){
//        this.items.add(orderItem);
//        if(orderItem.getOrders() != this){
//            orderItem.setOrders(this);
//        }
//    }

    public Long getOrderId(){
        return this.id;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", items=" + items +
                ", order_date=" + order_date +
                '}';
    }
}
