package reservpurchase.service.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash
public class EmailEntity {

    @Id
    private String email;
    private String certificationNumber;

}
