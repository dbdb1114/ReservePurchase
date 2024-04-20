package reservpurchase.service.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import reservpurchase.service.dto.MemberDto;
import reservpurchase.service.entity.MemberRedisEntity;
import reservpurchase.service.repository.MemberRedisRepository;
import reservpurchase.service.util.EmailProvider;
import reservpurchase.service.vo.request.EmailCertificationRequestVo;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{


    private EmailProvider emailProvider;
    private MemberRedisRepository memberRedisRepository;

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
        if(memberRedisEntity.getCertificationNumber().equals(emailCertificationRequestVo.getNumber())){
            return null;
        }
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(memberRedisEntity, MemberDto.class);
    }

    private static String getCertificationNumber(){

        String certificationNumber = "";

        for (int i = 0; i < 4; i++) certificationNumber += (int) (Math.random() * 10);

        return certificationNumber;
    }
}
