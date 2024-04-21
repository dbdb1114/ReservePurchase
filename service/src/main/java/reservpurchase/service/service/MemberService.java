package reservpurchase.service.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import reservpurchase.service.dto.MemberDto;
import reservpurchase.service.vo.response.ResponseMember;
import reservpurchase.service.vo.response.auth.ResponseVo;

public interface MemberService extends UserDetailsService {
    MemberDto join(MemberDto memberDto);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

    MemberDto tempJoin(MemberDto memberDto);

    MemberDto getUserDetailsByEmail(String email);

    ResponseMember updateMember(MemberDto memberDto);
}
