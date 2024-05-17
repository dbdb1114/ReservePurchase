package com.resurvepurchase.authservice.service;

import com.resurvepurchase.authservice.entity.Member;

public interface AuthService {
    Member login(Member member);
    Member validateToken(String token);
}
