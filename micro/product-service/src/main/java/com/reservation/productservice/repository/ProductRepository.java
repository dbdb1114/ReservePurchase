package com.reservation.productservice.repository;

import com.reservation.productservice.dto.ProductDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.reservation.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByCategoryId(int categoryId, Pageable pageable);
    @Query("select p.stock from Product p where p.id = :productId")
    Long findStockById(Long productId);

    ArrayList<Product> findAllByIdIn(List<Long> idList);
}
