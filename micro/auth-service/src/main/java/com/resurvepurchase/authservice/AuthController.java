package com.resurvepurchase.authservice;

import com.resurvepurchase.authservice.entity.Member;
import com.resurvepurchase.authservice.entity.Token;
import com.resurvepurchase.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public Token login(@RequestBody Member member) {
        return null;
    }
}
