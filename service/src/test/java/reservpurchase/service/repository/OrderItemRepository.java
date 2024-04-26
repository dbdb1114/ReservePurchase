package reservpurchase.service.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reservpurchase.service.entity.OrderEntity;
import reservpurchase.service.entity.OrderItemEntity;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity,Long> {
    List<OrderItemEntity> findByOrders(OrderEntity orders);
}
