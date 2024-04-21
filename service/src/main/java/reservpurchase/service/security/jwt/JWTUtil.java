package reservpurchase.service.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import reservpurchase.service.dto.MemberDto;

@Component
public class JWTUtil {
    private Environment env;
    private final Key key;

    public JWTUtil(Environment env) {
        this.env = env;
        byte[] keyBytes = Decoders.BASE64.decode(env.getProperty("token.secret"));
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String publish(MemberDto memberDetails){
        /*** 토큰 만들기
         * JWT 검증하여 검증 실패했을 경우
         * 클라이언트가 RefreshToken도 보냈는지 확인한다.
         * 클라이언트가 RefreshToken도 보내지 않았다면, 토큰이 만료됐음을 알린다.
         *  토큰이 만료됐음을 알리는 방식 => ResponseEntity에 토큰 만료됐음을 알리는 코드와 메세지를 보낸다.
         * **/
        return Jwts.builder()
                .setSubject(memberDetails.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() +
                        Long.parseLong(env.getProperty("token.expiration_time"))))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private boolean isValid(String jwt) {
        boolean returnValue = true;

        String subject = null;

        try {
            subject = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build().parseClaimsJws(jwt).getBody().getSubject();
        } catch (Exception ex){
            returnValue = false;
        }

        if(subject == null || subject.isEmpty()){
            returnValue = false;
        } else {

        }

        System.out.println("subject = " + subject);
        System.out.println("jwt = " + jwt);

        return returnValue;
    }
}
