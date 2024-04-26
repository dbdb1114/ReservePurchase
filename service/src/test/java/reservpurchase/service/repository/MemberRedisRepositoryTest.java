package reservpurchase.service.repository;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reservpurchase.service.entity.MemberRedisEntity;

@SpringBootTest
class MemberRedisRepositoryTest {

    @Autowired
    MemberRedisRepository memberRedisRepository;

    @Test
    void 삽입(){
        MemberRedisEntity memberRedisEntity = new MemberRedisEntity();
//        memberRedisEntity.setEmail("dbdb1114@naver.com");
//        memberRedisEntity.setName("유정현");

        System.out.println("======================================================");
        System.out.println("======================================================");
        System.out.println("======================================================");

        MemberRedisEntity save = memberRedisRepository.save(memberRedisEntity);
        System.out.println("save = " + save);

//        Optional<MemberRedisEntity> byId = memberRedis.findById(save.getEmail());
//        MemberRedisEntity byEmail = memberRedis.findByEmail(save.getEmail());
//        System.out.println("byId = " + byId.get());
//        System.out.println("byEmail = " + byEmail);


        System.out.println("======================================================");
        System.out.println("======================================================");
        System.out.println("======================================================");
        System.out.println("======================================================");
        long count = memberRedisRepository.count();
        System.out.println("count = " + count);
        Optional<MemberRedisEntity> byId = memberRedisRepository.findById("dbdb1114@naver.com");
        System.out.println("byId.get() = " + byId.get());
        MemberRedisEntity byEmail = memberRedisRepository.findByEmail("dbdb1114@naver.com");
        System.out.println("byEmail = " + byEmail);

    }

}
