package reservpurchase.service.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import reservpurchase.service.dto.MemberDto;
import reservpurchase.service.entity.MemberEntity;
import reservpurchase.service.entity.embeded.Address;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 저장(){

        //given
        MemberEntity mEntity = new MemberEntity();
        mEntity.setEmail("dbdb1114@naver.com");
        mEntity.setUsername("유정현");
        mEntity.setPassword("1234");

        //when
        MemberEntity save = memberRepository.save(mEntity);

        //then
        System.out.println(save);

        assertThat(save.getEmail()).isEqualTo("dbdb1114@naver.com");
        assertThat(save.getUsername()).isEqualTo("유정현");
        assertThat(save.getPassword()).isEqualTo("1234");
    }

    @Test
    void 주소값_저장() {

        //given
        MemberEntity mEntity = new MemberEntity();
        Address address = new Address("경기도 성남시 중원구 하대원동", "성원초원아파트 103동 1107호");

        mEntity.setEmail("dbdb1114@naver.com");
        mEntity.setUsername("유정현");
        mEntity.setPassword("1234");
        mEntity.setAddress(address);

        //when
        MemberEntity save = memberRepository.save(mEntity);

        //then
        System.out.println(save);

        assertThat(save.getEmail()).isEqualTo("dbdb1114@naver.com");
        assertThat(save.getUsername()).isEqualTo("유정현");
        assertThat(save.getPassword()).isEqualTo("1234");
        assertThat(save.getAddress().getCity()).isEqualTo("경기도 성남시 중원구 하대원동");
        assertThat(save.getAddress().getDetail()).isEqualTo("성원초원아파트 103동 1107호");
    }

}
