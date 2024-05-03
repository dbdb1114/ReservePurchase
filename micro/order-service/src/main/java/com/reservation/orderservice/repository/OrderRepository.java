package com.reservation.orderservice.repository;

import com.reservation.orderservice.entity.Order;
import com.reservation.orderservice.entity.OrderStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    Long getLastInsertId();

    List<Order> findAllByMemberId(Long memberId);
    List<Order> findAllByOrderDateBetweenAndStatusIsIn(LocalDateTime stDate, LocalDateTime edDate, OrderStatus[] list);
    List<Order> findAllByOrderDateBetweenAndStatus(LocalDateTime stDate, LocalDateTime edDate, OrderStatus status);
}
