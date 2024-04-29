package com.reservation.productservice.repository;

import com.reservation.productservice.entity.WishList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList,Long> {
    List<WishList> findAllByMemberId(Long memberId);
}
