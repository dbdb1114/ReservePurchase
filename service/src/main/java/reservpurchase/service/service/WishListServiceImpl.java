package reservpurchase.service.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reservpurchase.service.dto.WishListDto;
import reservpurchase.service.entity.WishListEntity;
import reservpurchase.service.repository.WishListRepository;

@Service
@AllArgsConstructor
public class WishListServiceImpl implements WishListService{

    WishListRepository wishListRepository;
    ModelMapper modelMapper;

    @Override
    public WishListDto addProduct(WishListDto wishListDto) {
        WishListEntity entity = modelMapper.map(wishListDto, WishListEntity.class);
        WishListEntity save = wishListRepository.save(entity);
        return modelMapper.map(save, WishListDto.class);
    }

    @Override
    public WishListEntity selectWish(Long wishId) {
        WishListEntity wishListEntity = wishListRepository.findById(wishId).get();
        System.out.println("wishListEntity = " + wishListEntity);
        return wishListEntity;
    }

    @Override
    @Transactional
    public Integer changeQuantity(WishListDto wishListDto) {
        WishListEntity wishListEntity = wishListRepository.findById(wishListDto.getId()).get();
        wishListEntity.updateQuantity(wishListDto.getQuantity());
        return wishListDto.getQuantity();
    }

    @Override
    public Boolean delete(Long id) {
        wishListRepository.delete(WishListEntity.builder().id(id).build());
        boolean result = !wishListRepository.existsById(id);
        return result;
    }
}
