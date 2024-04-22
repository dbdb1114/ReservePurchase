package reservpurchase.service.vo.request;

import lombok.Data;

@Data
public class RequestWishList {
    String email;
    Long productId;
    Integer count;
}
