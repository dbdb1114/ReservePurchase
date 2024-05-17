package com.resurvepurchase.authservice.service;

import com.resurvepurchase.authservice.entity.Member;
import com.resurvepurchase.authservice.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{

    MemberRepository memberRepository;

    @Override
    public Member login(Member member) {
        Optional<Member> savedMember = memberRepository.findByEmail(member.getEmail());
        savedMember.or
        return null;
    }

    @Override
    public Member validateToken(String token) {
        return null;
    }
}
