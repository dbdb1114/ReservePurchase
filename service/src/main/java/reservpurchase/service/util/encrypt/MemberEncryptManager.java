package reservpurchase.service.util.encrypt;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import reservpurchase.service.dto.MemberDto;
import reservpurchase.service.entity.embeded.Address;

@Component
public class MemberEncryptManager extends EncryptManager {

    /**
     * MemberEncryptManager 또한 생성자를 만들 필요 없으므로
     * 아래와 같이 활용
     * */
    public MemberEncryptManager(Environment env) {super(env);}

    public String encodePassword(String plainPassword){
        return passwordEncoder.encode(plainPassword);
    }

    public MemberDto encodeAll(MemberDto memberDto) {

        try{
            memberDto.setEmail(infoEncode(memberDto.getEmail()));
            memberDto.setName(infoEncode(memberDto.getName()));
            memberDto.setPassword(encodePassword(memberDto.getPassword()));

            Address address = memberDto.getAddress();

            if(address != null){
                address.setCity(infoEncode(address.getCity()));
                address.setDetailAddress(infoEncode(address.getDetailAddress()));
            }

        } catch (Exception e){
            handleEncodeException(e);
        }

        return memberDto;
    }

    public MemberDto decodeAll(MemberDto memberDto) {

        try{
            memberDto.setEmail(infoDecode(memberDto.getEmail()));
            memberDto.setName(infoDecode(memberDto.getName()));

            Address address = memberDto.getAddress();
            if(address != null){
                address.setCity(infoDecode(address.getCity()));
                address.setDetailAddress(infoEncode(address.getDetailAddress()));
            }

        } catch (Exception e){
            handleEncodeException(e);
        }

        return memberDto;
    }

    private void handleEncodeException(Exception e) {
        System.out.println(e.getMessage());
    }

}
