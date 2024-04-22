package reservpurchase.service.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
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
}
