package com.reservation.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "MEMBER-SERVICE", url = "localhost:8000/api/v1/member-service")
public interface MemberClient {
    @PostMapping(value = "/member-id")
    Long memberId(@RequestHeader(value = "Authorization") String authorization);
}
