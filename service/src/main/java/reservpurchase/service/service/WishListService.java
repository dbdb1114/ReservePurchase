package reservpurchase.service.service;


import reservpurchase.service.dto.WishListDto;

public interface WishListService {
    WishListDto addProduct(WishListDto wishListDto);
    Integer changeQuantity(WishListDto wishListDto);
    Boolean delete(Long id);
}
