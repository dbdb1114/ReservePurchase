package reservpurchase.service.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reservpurchase.service.entity.OrderEntity;
import reservpurchase.service.entity.MemberEntity;
import reservpurchase.service.entity.ProductEntity;
import reservpurchase.service.entity.OrderItemEntity;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class OrderRepositoryTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Test
    @BeforeEach
    @Transactional
    void 단건구매테스트(){

    }
}
