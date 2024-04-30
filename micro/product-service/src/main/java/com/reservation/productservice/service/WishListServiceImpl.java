package com.reservation.productservice.service;

import com.reservation.productservice.dto.WishListDto;
import com.reservation.productservice.entity.WishList;
import com.reservation.productservice.repository.WishListRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class WishListServiceImpl implements WishListService{

    WishListRepository wishListRepository;
    ModelMapper modelMapper;

    @Override
    public List<WishList> memberWish(Long memberId) {
        List<WishList> memberWishList = wishListRepository.findAllByMemberId(memberId);
        return memberWishList;
    }

    @Override
    public WishListDto addWish(WishListDto wishListDto) {
        WishList entity = modelMapper.map(wishListDto, WishList.class);
        WishList save = wishListRepository.save(entity);
        return modelMapper.map(save, WishListDto.class);
    }

    @Override
    @Transactional
    public Integer changeQuantity(WishListDto wishListDto) {
        WishList wishListEntity = wishListRepository.findById(wishListDto.getId()).get();
        wishListEntity.updateQuantity(wishListDto.getQuantity());
        return wishListDto.getQuantity();
    }

    @Override
    public Boolean delete(Long id) {
//        wishListRepository.deleteWishListById(id);
        return true;
    }
}
