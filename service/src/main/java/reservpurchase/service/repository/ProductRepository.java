package reservpurchase.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reservpurchase.service.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Page<ProductEntity> findAllByCategoryId(int categoryId, Pageable pageable);
}
