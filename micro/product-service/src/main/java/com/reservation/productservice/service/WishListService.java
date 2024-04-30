package com.reservation.productservice.service;


import com.reservation.productservice.dto.WishListDto;
import com.reservation.productservice.entity.WishList;
import java.util.List;

public interface WishListService {
    List<WishList> memberWish(Long memberId);
    WishListDto addWish(WishListDto wishListDto);
    Integer changeQuantity(WishListDto wishListDto);
    Boolean delete(Long id);

}
