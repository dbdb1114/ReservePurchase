package reservpurchase.service.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import reservpurchase.service.dto.MemberDto;

public interface MemberService extends UserDetailsService {
    MemberDto join(MemberDto memberDtoDto);
}
