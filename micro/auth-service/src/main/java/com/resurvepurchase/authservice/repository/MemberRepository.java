package com.resurvepurchase.authservice.repository;


import com.resurvepurchase.authservice.entity.Member;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.Optional;

@Repository
public interface MemberRepository extends ReactiveCrudRepository<Member, Long>{
    Optional<Member> findByEmail(String email);
}
