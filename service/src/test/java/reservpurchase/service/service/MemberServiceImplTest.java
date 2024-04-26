package reservpurchase.service.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.security.InvalidAlgorithmParameterException;
import reservpurchase.service.entity.embeded.Address;
import javax.crypto.IllegalBlockSizeException;
import java.security.NoSuchAlgorithmException;
import reservpurchase.service.dto.MemberDto;
import java.io.UnsupportedEncodingException;
import javax.crypto.NoSuchPaddingException;
import org.junit.jupiter.api.DisplayName;
import java.security.InvalidKeyException;
import javax.crypto.BadPaddingException;
import org.junit.jupiter.api.Test;

@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("Address 없이 저장")
    public void 저장_Address_X() {
        //given
        MemberDto memberDto = new MemberDto("dbdb1114@naver.com", "유정현", "1234", "123456789", null);

        //when
        MemberDto savedDto = memberService.join(memberDto);

        //then
        assertThat(savedDto).equals(memberDto);
    }

    @Test
    @DisplayName("Address 포함 저장")
    public void 저장_Address_O()
            throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        //given
        Address address = new Address("경기도 성남시 중원구 하대원동", "성원초원아파트 103동 1107호");
        MemberDto memberDto = new MemberDto("dbdb1114@naver.com", "유정현", "1234", "123456789", address);

        //when
        MemberDto savedDto = memberService.join(memberDto);

        //then
        assertThat(savedDto).isEqualTo(memberDto);
    }

}
