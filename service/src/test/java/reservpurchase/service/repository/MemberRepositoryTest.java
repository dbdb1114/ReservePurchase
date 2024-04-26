package reservpurchase.service.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.EnumSource.Mode;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import reservpurchase.service.dto.MemberDto;
import reservpurchase.service.entity.MemberEntity;
import reservpurchase.service.entity.embeded.Address;
import reservpurchase.service.util.encrypt.EncryptManager;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ModelMapper modelMapper;

    @Test
    @DisplayName("없을때")
    void search(){
        System.out.println(memberRepository.findByEmail("asdfasf"));
    }

    @Test
    @DisplayName("이메일 중복 및 전화번호 중복체크")
    public void 유무체크(){
        //given
        MemberEntity mEntity = MemberEntity.builder()
                .email("dbdb1114@naver.com")
                .name("유정현")
                .phone("123456789")
                .password("1234")
                .build();

        //when
        memberRepository.save(mEntity);
        boolean existsByEmail = memberRepository.existsByEmail("dbdb1114@naver.com");
        boolean existsByPhone = memberRepository.existsByPhone("123456789");

        //then
        assertThat(existsByEmail).isTrue();
        assertThat(existsByPhone).isTrue();
    }


    @Test
    void 주소값_저장() {

        //given
        Address address = new Address("경기도 성남시 중원구 하대원동", "성원초원아파트 103동 1107호");
        MemberEntity mEntity = MemberEntity.builder()
                .email("dbdb11114@naver.com")
                .name("유정현")
                .password("1234")
                .phone("123456789")
                .address(address)
                .build();;


        System.out.println("mEntity = " + mEntity);

        //when
        MemberEntity save = memberRepository.save(mEntity);
        MemberEntity reffer = MemberEntity
                                .builder()
                                .email("dbdb1114@naver.com")
                                .name("유정현")
                                .password("1234")
                                .phone("123456789")
                                .address(address)
                                .build();

        //then
        System.out.println(save);


        assertThat(reffer).isEqualTo(save);
    }


    @Test
    void 조회(){
        String email = "dbdb1114@naver.com";
        MemberEntity byEmail = memberRepository.findByEmail(EncryptManager.infoEncode(email));
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);
        MemberDto map = mapper.map(byEmail, MemberDto.class);
        map.decodeAll();
    }


}
