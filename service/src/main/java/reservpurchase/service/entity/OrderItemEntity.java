package reservpurchase.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDER_ITEM")
public class OrderItemEntity {

    @Id
    @Column(name = "ORDER_ITEM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "orders_id")
    private Long orderId;

    @OneToOne
    @JoinColumn(name = "PRODUCT_ID")
    private ProductEntity product;

    private Integer count;

    @Column(name = "ORDER_PRICE")
    private Integer orderPrice;

//
//    public OrderEntity getOrders(){
//        return this.orders;
//    }
//    public void setOrders(OrderEntity orders) {
//        this.orders = orders;
//    }

    @Override
    public String toString() {
        return "OrderItemEntity{" +
                "id=" + id +
                ", product=" + product +
                ", count=" + count +
                ", orderPrice=" + orderPrice +
                '}';
    }
}
