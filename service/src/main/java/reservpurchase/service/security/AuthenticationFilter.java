package reservpurchase.service.security;

import reservpurchase.service.dto.MemberDto;
import reservpurchase.service.service.MemberService;
import reservpurchase.service.security.jwt.JWTUtil;
import reservpurchase.service.vo.request.RequestLogin;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private JWTUtil jwtUtil;
    private MemberService memberService;

    public AuthenticationFilter(AuthenticationManager authenticationManager, MemberService memberService, JWTUtil jwtUtil) {
        super.setAuthenticationManager(authenticationManager);
        this.memberService = memberService;
        this.jwtUtil = jwtUtil;
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
        MemberDto memberDetails = memberService.getUserDetailsByEmail(userName);

        String token = jwtUtil.publish(memberDetails);

        response.addHeader("token", token);
        response.addHeader("email", memberDetails.getEmail());
    }
}
