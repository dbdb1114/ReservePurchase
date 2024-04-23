package reservpurchase.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "WISHLIST")
public class WishListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "MEMBER_ID")
    private Long memberId;

    @OneToOne
    @JoinColumn(name = "PRODUCT_ID")
    private ProductEntity product;
    private Integer quantity;
    public void updateQuantity(int quantity){
        this.quantity = quantity;
    }

}
