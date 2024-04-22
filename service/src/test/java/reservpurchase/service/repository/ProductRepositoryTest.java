package reservpurchase.service.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import reservpurchase.service.entity.ProductEntity;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void 책조회(){
        //given
        List<ProductEntity> all = productRepository.findAll();

        //when
        for (ProductEntity productEntity : all) {
            System.out.println(productEntity);
        }

        //then
        System.out.println("출력 직접 확인");

    }

    @Test
    void 페이징() {
        //given
        // 페이지 Request 정의
        // 1페이지 10개 => 11~20
        PageRequest pageRequest = PageRequest.of(1,10, Sort.by(Sort.Direction.DESC,"id"));
        System.out.println("pageRequest = " + pageRequest);

        //when
        Page<ProductEntity> page = productRepository.findAllByCategoryId(1,pageRequest);
        System.out.println("page = " + page);

        for (ProductEntity productEntity : page) {
            System.out.println(productEntity);
        }

        //then
        List<ProductEntity> content = page.getContent();
        assertThat(content.size()).isEqualTo(10); // 10개를 가져오기 때문에 10개
        assertThat(page.getTotalElements()).isEqualTo(30); // 2번 카테고리의 상품 수
    }
}
