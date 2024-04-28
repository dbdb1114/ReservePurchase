package com.reservation.userservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reservation.userservice.service.MemberService;
import com.reservation.userservice.util.encrypt.EncryptManager;
import com.reservation.userservice.vo.request.RequestLogin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private MemberService memberService;
    private Environment env;
    private final Key key;

    public AuthenticationFilter(AuthenticationManager authenticationManager, MemberService memberService, Environment env) {
        super.setAuthenticationManager(authenticationManager);
        this.memberService = memberService;
        this.env = env;
        byte[] keyBytes = Decoders.BASE64.decode(env.getProperty("token.secret"));
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try{
            RequestLogin creds = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);

            // 인증하기 위해서 아이디와 패스워드를 토큰으로 변환한다.
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>()
                    )
            );

        } catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String userName = ((User) authResult.getPrincipal()).getUsername();

        String token = Jwts.builder()
                .setSubject(EncryptManager.infoDecode(userName))
                .setExpiration(new Date(System.currentTimeMillis() +
                        Long.parseLong(env.getProperty("token.expiration_time"))))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        response.addHeader("token", token);
        response.addHeader("email", EncryptManager.infoDecode(userName));
    }
}
