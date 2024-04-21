package reservpurchase.service.service;

import static reservpurchase.service.util.email.EmailProvider.getCertificationNumber;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reservpurchase.service.dto.MemberDto;
import reservpurchase.service.entity.MemberRedisEntity;
import reservpurchase.service.repository.MemberRedisRepository;
import reservpurchase.service.util.email.EmailProvider;
import reservpurchase.service.vo.request.EmailCertificationRequestVo;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

    private EmailProvider emailProvider;
    private MemberRedisRepository memberRedisRepository;
    private ModelMapper modelMapper;

    @Override
    public String emailCertification(String email) {
        String certificationNumber = getCertificationNumber();

        try {
            emailProvider.sendCertificationMail(email, certificationNumber);
        } catch (Exception e){
            e.printStackTrace();
            return "0";
        }

        return certificationNumber;
    }

    @Override
    public MemberDto certificate(EmailCertificationRequestVo emailCertificationRequestVo) {
        MemberRedisEntity memberRedisEntity = memberRedisRepository
                .findById(emailCertificationRequestVo.getEmail()).get();
        if(memberRedisEntity.certification(emailCertificationRequestVo.getNumber())){
            return modelMapper.map(memberRedisEntity, MemberDto.class);
        }
        return null;
    }


}
