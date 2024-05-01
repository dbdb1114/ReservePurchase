package com.reservation.productservice.repository;

import com.reservation.productservice.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query("select s from Stock s where s.product.id = :productId")
    Stock findByProductId(Long productId);
}
