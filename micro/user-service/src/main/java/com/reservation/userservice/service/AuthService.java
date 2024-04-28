package com.reservation.userservice.service;


import com.reservation.userservice.dto.MemberDto;
import com.reservation.userservice.vo.request.EmailCertificationRequestVo;

public interface AuthService {

    String emailCertification(String email);
    MemberDto certificate(EmailCertificationRequestVo emailCertificationRequestVo);

}
