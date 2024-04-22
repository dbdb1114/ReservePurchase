package reservpurchase.service.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reservpurchase.service.entity.ProductEntity;
import reservpurchase.service.entity.WishListEntity;

@SpringBootTest
class WishListRepositoryTest {

    @Autowired
    WishListRepository wishListRepository;

    @Test
    void 저장(){
        //given
        WishListEntity wishListEntity = WishListEntity.builder()
                .memberId(1L)
                .product(ProductEntity.builder().id(1L).build())
                .count(1)
                .build();

        //when
        wishListRepository.save(wishListEntity);

        //then

        List<WishListEntity> all = wishListRepository.findAll();
        for (WishListEntity listEntity : all) {
            System.out.println(listEntity);
        }
        Assertions.assertThat(all.size()).isEqualTo(1);
    }

}
