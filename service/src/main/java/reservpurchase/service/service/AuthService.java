package reservpurchase.service.service;

import reservpurchase.service.dto.MemberDto;
import reservpurchase.service.vo.request.EmailCertificationRequestVo;

public interface AuthService {

    String emailCertification(String email);
    MemberDto certificate(EmailCertificationRequestVo emailCertificationRequestVo);

}
