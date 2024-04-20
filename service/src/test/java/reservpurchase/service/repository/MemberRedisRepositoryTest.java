package reservpurchase.service.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import reservpurchase.service.entity.MemberRedisEntity;

@SpringBootTest
@Transactional
class MemberRedisRepositoryTest {

    @Autowired
    MemberRedisRepository repository;

    @Test
    @DisplayName("주소 없음")
    void 저장확인(){

        MemberRedisEntity mEntity = new MemberRedisEntity();

        mEntity.setEmail("dbdb1114@naver.com");
        mEntity.setName("유정현");
        mEntity.setPassword("1234");
        mEntity.setPhone("123456789");

        repository.save(mEntity);
    }

}
