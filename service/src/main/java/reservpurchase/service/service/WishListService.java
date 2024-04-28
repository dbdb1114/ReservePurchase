package reservpurchase.service.service;


import org.springframework.http.ResponseEntity;
import reservpurchase.service.dto.WishListDto;
import reservpurchase.service.entity.WishListEntity;

public interface WishListService {
    WishListDto addProduct(WishListDto wishListDto);
    Integer changeQuantity(WishListDto wishListDto);
    Boolean delete(Long id);

    WishListEntity selectWish(Long wishId);
}
