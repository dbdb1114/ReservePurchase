package com.reservation.userservice.repository;

import com.reservation.userservice.entity.MemberRedis;
import org.springframework.data.repository.CrudRepository;

public interface MemberRedisRepository extends CrudRepository<MemberRedis,String> {
    MemberRedis findByEmail(String asdfasdf);
}
