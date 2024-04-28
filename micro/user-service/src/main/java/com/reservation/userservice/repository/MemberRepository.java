package com.reservation.userservice.repository;

import com.reservation.userservice.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    String findNameByEmailOrPhone(String email, String phone);
    Member findByEmail(String email);

    @Query("select m.id from Member m where m.email = :email")
    Long findIdByEmail(@Param("email") String email);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

}
