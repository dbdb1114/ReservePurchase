package reservpurchase.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import reservpurchase.service.entity.ProductEntity;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WishListDto {

    private Long id;
    private Long memberId;
    private ProductDto product;
    private Integer count;

    public Integer getCount(){
        return this.count;
    }

    @Override
    public String toString() {
        return "WishListDto{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", count=" + count +
                '}';
    }
}
