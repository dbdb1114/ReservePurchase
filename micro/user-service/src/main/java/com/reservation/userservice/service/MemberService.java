package com.reservation.userservice.service;

import com.reservation.userservice.dto.MemberDto;
import com.reservation.userservice.vo.response.ResponseMember;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {
    MemberDto join(MemberDto memberDto);
    MemberDto tempJoin(MemberDto memberDto);
    ResponseMember updateMember(MemberDto memberDto);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    Long findIdByEmail(String email);

    MemberDto getUserDetailsByEmail(String email);

}
