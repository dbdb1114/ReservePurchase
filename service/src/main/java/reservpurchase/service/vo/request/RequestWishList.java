package reservpurchase.service.vo.request;

import lombok.Data;

@Data
public class RequestWishList {
    Long id;
    String email;
    Long productId;
    Integer quantity;
}
