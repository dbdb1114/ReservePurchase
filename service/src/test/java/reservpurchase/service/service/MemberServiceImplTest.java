package reservpurchase.service.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import reservpurchase.service.dto.MemberDto;
import reservpurchase.service.entity.embeded.Address;

@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("Address 없이 저장")
    public void 저장_Address_X(){
        //given
        MemberDto memberDto = new MemberDto("dbdb1114@naver.com", "유정현", "1234", null);

        //when
        MemberDto savedDto = memberService.join(memberDto);

        //then
        assertThat(savedDto.getEmail()).isEqualTo(memberDto.getEmail());
        assertThat(savedDto.getUsername()).isEqualTo(memberDto.getUsername());
        assertThat(savedDto.getPassword()).isEqualTo(memberDto.getPassword());
        assertThat(savedDto.getAddress()).isNull();
    }

    @Test
    @DisplayName("Address 포함 저장")
    public void 저장_Address_O(){
        //given
        Address address = new Address("경기도 성남시 중원구 하대원동", "성원초원아파트 103동 1107호");
        MemberDto memberDto = new MemberDto("dbdb1114@naver.com", "유정현", "1234", address);

        //when
        MemberDto savedDto = memberService.join(memberDto);

        //then
        assertThat(savedDto.getEmail()).isEqualTo(memberDto.getEmail());
        assertThat(savedDto.getUsername()).isEqualTo(memberDto.getUsername());
        assertThat(savedDto.getPassword()).isEqualTo(memberDto.getPassword());
        assertThat(savedDto.getAddress().getCity()).isEqualTo(address.getCity());
        assertThat(savedDto.getAddress().getDetail()).isEqualTo(address.getDetail());
    }

}
