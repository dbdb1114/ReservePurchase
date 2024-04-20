package reservpurchase.service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import reservpurchase.service.entity.embeded.Address;

@Data
@RedisHash(value = "member", timeToLive = 3600) // 한 시간 이내에 인증해야함.
public class MemberRedisEntity {

    @Id
    private String email; // 이메일
    private String name; // 이름
    private String password; // 비밀번호
    private String phone;
    private String certificationNumber;
    private Boolean emailAuthentication;
    private Address address;

}
