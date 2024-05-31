package com.reservation.stockservice.repository;

import com.reservation.stockservice.entity.StockJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockJpa,Long> {

    StockJpa findByProductId(Long productId);
}
