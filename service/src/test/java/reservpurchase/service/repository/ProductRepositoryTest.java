package reservpurchase.service.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
}
