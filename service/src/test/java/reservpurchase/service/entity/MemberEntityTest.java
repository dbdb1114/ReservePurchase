package reservpurchase.service.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reservpurchase.service.repository.MemberRepository;

@SpringBootTest
class MemberEntityTest {

    @Autowired
    MemberRepository memberRepository;


    @Test
    void 멤버조회(){
        //given
        MemberEntity byEmail = memberRepository.findByEmail("dbdb1114@hanmail.com");

        //when
        System.out.println("byEmail = " + byEmail);

        //then

    }

}
