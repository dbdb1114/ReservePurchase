package com.resurvepurchase.authservice.controller;

import com.resurvepurchase.authservice.entity.Member;
import com.resurvepurchase.authservice.entity.Token;
import com.resurvepurchase.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/login/{email}/{password}")
    public Token login(@RequestParam String email, @RequestParam String password) {
        Member member = new Member(email, password);
        authService.login(member);
        return null;
    }
}
