package reservpurchase.service.entity;

import jakarta.persistence.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
public class EmailEntity {

    @Id
    private String email;
    private String certificationNumber;

}
