package reservpurchase.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reservpurchase.service.entity.WishListEntity;

public interface WishListRepository extends JpaRepository<WishListEntity,Long> {
}
